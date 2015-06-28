package uk.co.grahamcox.dirt.authentication.external.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Mechanism to load an Access Token for a user during authentication
 */
public class AccessTokenLoader {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(GoogleExternalAuthenticationProvider.class);

    /** The name of the parameter for the Client ID */
    private static final String CLIENT_ID_PARAM = "client_id";

    /** The name of the parameter for the Redirect URI*/
    private static final String REDIRECT_URI_PARAM = "redirect_uri";

    /** The name of the parameter for the Authorization Code*/
    private static final String CODE_PARAM = "code";

    /** The name of the parameter for the Grant Type*/
    private static final String GRANT_TYPE_PARAM = "grant_type";

    /** The name of the parameter for the Client Secret */
    private static final String CLIENT_SECRET_PARAM = "client_secret";

    /** The grant type to request */
    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";

    /** The Client ID to use */
    private final String clientId;

    /** The Client Secret to use */
    private final String clientSecret;

    /** The Redirect URI to use */
    private final URI redirectUri;

    /** The Token Endpoint to use */
    private final URI tokenEndpoint;

    /** The rest template to use */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Construct the Access Token loader
     * @param clientId the Client ID
     * @param clientSecret the Client Secret
     * @param redirectUri the Redirect URI
     * @param tokenEndpoint the Google Token Endpoint
     */
    public AccessTokenLoader(final String clientId,
        final String clientSecret,
        final URI redirectUri,
        final URI tokenEndpoint) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenEndpoint = tokenEndpoint;
    }

    /**
     * Load the Access Token for the given Authentication Code
     * @param code the Authentication Code
     * @return the Access Token
     */
    public String loadAccessToken(final String code) {
        LOG.debug("Completing Google Authentication for Client ID {} and Authentication Code {}", clientId, code);

        MultiValueMap<String, String> tokenParams = new LinkedMultiValueMap<>();
        tokenParams.add(CLIENT_ID_PARAM, clientId);
        tokenParams.add(CLIENT_SECRET_PARAM, clientSecret);
        tokenParams.add(REDIRECT_URI_PARAM, redirectUri.toString());
        tokenParams.add(CODE_PARAM, code);
        tokenParams.add(GRANT_TYPE_PARAM, GRANT_TYPE_AUTHORIZATION_CODE);
        LOG.debug("Access Token Request: {}", tokenParams);

        AccessTokenResponse token = restTemplate.postForObject(tokenEndpoint,
            tokenParams,
            AccessTokenResponse.class);
        LOG.debug("Access Token: {}", token);
        String accessToken = token.getAccessToken();

        return accessToken;
    }
}
