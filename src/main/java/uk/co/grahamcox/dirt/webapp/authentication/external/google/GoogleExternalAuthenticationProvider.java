/*
 * Copyright (C) 21/06/15 graham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.grahamcox.dirt.webapp.authentication.external.AuthenticationResponse;
import uk.co.grahamcox.dirt.webapp.authentication.external.AuthenticationStatus;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationProvider;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationRequest;

/**
 * External Authentication Provider for logging in with Google
 */
public class GoogleExternalAuthenticationProvider implements ExternalAuthenticationProvider {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(GoogleExternalAuthenticationProvider.class);

    /** The name of the parameter for the Client ID */
    private static final String CLIENT_ID_PARAM = "client_id";

    /** The name of the parameter for the Response Type */
    private static final String RESPONSE_TYPE_PARAM = "response_type";

    /** The name of the parameter for the Scope */
    private static final String SCOPE_PARAM = "scope";

    /** The name of the parameter for the Redirect URI*/
    private static final String REDIRECT_URI_PARAM = "redirect_uri";

    /** The name of the parameter for the State */
    private static final String STATE_PARAM = "state";

    /** The name of the parameter for the Authorization Code*/
    private static final String CODE_PARAM = "code";

    /** The name of the parameter for the Grant Type*/
    private static final String GRANT_TYPE_PARAM = "grant_type";

    /** The name of the parameter for the Client Secret */
    private static final String CLIENT_SECRET_PARAM = "client_secret";

    /** The response type to request */
    private static final String RESPONSE_TYPE_CODE = "code";

    /** The scopes to request */
    private static final String SCOPES = "openid email profile";

    /** The grant type to request */
    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    /** The Client ID to use */
    private final String clientId;

    /** The Client Secret to use */
    private final String clientSecret;

    /** The Redirect URI to use */
    private final URI redirectUri;

    /** The Authentication Endpoint to use */
    private final URI authenticationEndpoint;

    /** The Token Endpoint to use */
    private final URI tokenEndpoint;

    /** The Profile Endpoint to use */
    private final URI profileEndpoint;

    /** The rest template to use */
    private RestTemplate restTemplate = new RestTemplate();
    /**
     * Construct the provider
     * @param clientId the Client ID
     * @param clientSecret the Client Secret
     * @param redirectUri the Redirect URI to use
     * @param authenticationEndpoint the Authentication Endpoint
     * @param tokenEndpoint the Token Endpoint
     * @param profileEndpoint the Profile Endpoint
     */
    public GoogleExternalAuthenticationProvider(final String clientId,
        final String clientSecret,
        final URI redirectUri,
        final URI authenticationEndpoint,
        final URI tokenEndpoint,
        final URI profileEndpoint) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.authenticationEndpoint = authenticationEndpoint;
        this.tokenEndpoint = tokenEndpoint;
        this.profileEndpoint = profileEndpoint;
    }

    /**
     * Request that authentication be started with the given provider
     *
     * @return the request to pass on to the browser for authentication
     */
    @Override
    public ExternalAuthenticationRequest requestAuthentication() {
        String state = UUID.randomUUID().toString();
        LOG.debug("Starting Google Authentication for Client ID {} and State {}", clientId, state);
        URI resultUri = UriComponentsBuilder.fromUri(authenticationEndpoint)
            .queryParam(CLIENT_ID_PARAM, clientId)
            .queryParam(RESPONSE_TYPE_PARAM, RESPONSE_TYPE_CODE)
            .queryParam(SCOPE_PARAM, SCOPES)
            .queryParam(REDIRECT_URI_PARAM, this.redirectUri.toString())
            .queryParam(STATE_PARAM, state)
            .build()
            .toUri();

        return new ExternalAuthenticationRequest(state, resultUri);
    }

    /**
     * Complete authentication, using the given parameters for the authentication provider
     *
     * @param params the parameters to complete authentication with
     * @return the result of completing authentication
     */
    @Override
    public AuthenticationResponse completeAuthentication(final Map<String, String> params) {
        LOG.debug("Completing Google Authentication for Client ID {} and State {}", clientId, params.get(STATE_PARAM));

        MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add(CLIENT_ID_PARAM, clientId);
        tokenParams.add(CLIENT_SECRET_PARAM, clientSecret);
        tokenParams.add(REDIRECT_URI_PARAM, redirectUri.toString());
        tokenParams.add(CODE_PARAM, params.get(CODE_PARAM));
        tokenParams.add(GRANT_TYPE_PARAM, GRANT_TYPE_AUTHORIZATION_CODE);
        LOG.debug("Access Token Request: {}", tokenParams);

        ResponseEntity<Map> token = restTemplate.postForEntity(tokenEndpoint, tokenParams, Map.class);
        LOG.debug("Access Token: {}", token);
        String accessToken = (String) token.getBody().get("access_token");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        ResponseEntity<Map> profile = restTemplate.exchange(new RequestEntity<>(httpHeaders,
                HttpMethod.GET,
                profileEndpoint),
            Map.class);
        LOG.debug("Profile: {}", profile);

        return new AuthenticationResponse("Google", AuthenticationStatus.SUCCESS);
    }
}
