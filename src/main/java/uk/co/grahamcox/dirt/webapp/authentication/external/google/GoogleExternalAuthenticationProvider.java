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

import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.grahamcox.dirt.webapp.authentication.external.AuthenticationResponse;
import uk.co.grahamcox.dirt.webapp.authentication.external.AuthenticationStatus;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationProvider;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationRequest;

import java.net.URI;
import java.util.UUID;

/**
 * External Authentication Provider for logging in with Google
 */
public class GoogleExternalAuthenticationProvider implements ExternalAuthenticationProvider {
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

    /**
     * Construct the provider
     * @param clientId the Client ID
     * @param clientSecret the Client Secret
     * @param redirectUri the Redirect URI to use
     * @param authenticationEndpoint the Authentication Endpoint
     * @param tokenEndpoint the Token Endpoint
     */
    public GoogleExternalAuthenticationProvider(final String clientId,
        final String clientSecret,
        final URI redirectUri,
        final URI authenticationEndpoint,
        final URI tokenEndpoint) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.authenticationEndpoint = authenticationEndpoint;
        this.tokenEndpoint = tokenEndpoint;
    }

    /**
     * Request that authentication be started with the given provider
     *
     * @return the request to pass on to the browser for authentication
     */
    @Override
    public ExternalAuthenticationRequest requestAuthentication() {
        String state = UUID.randomUUID().toString();
        URI resultUri = UriComponentsBuilder.fromUri(authenticationEndpoint)
            .queryParam("client_id", clientId)
            .queryParam("response_type", "code")
            .queryParam("scope", "openid email profile")
            .queryParam("redirect_uri", this.redirectUri.toString())
            .queryParam("state", state)
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
        return new AuthenticationResponse("Google", AuthenticationStatus.SUCCESS);
    }
}
