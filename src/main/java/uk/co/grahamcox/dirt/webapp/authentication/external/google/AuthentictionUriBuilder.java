package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Mechanism to build the URI for Authentication by Google
 */
public class AuthentictionUriBuilder {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(AuthentictionUriBuilder.class);

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

    /** The response type to request */
    private static final String RESPONSE_TYPE_CODE = "code";

    /** The scopes to request */
    private static final String SCOPES = "openid email profile";

    /** The Client ID to use */
    private final String clientId;

    /** The Redirect URI to use */
    private final URI redirectUri;

    /** The Authentication Endpoint to use */
    private final URI authenticationEndpoint;

    /**
     * Construct the Authentication URI Builder
     * @param clientId the Client ID
     * @param redirectUri the Redirect URI
     * @param authenticationEndpoint the Authentication Endpoint
     */
    public AuthentictionUriBuilder(final String clientId,
        final URI redirectUri, final
        URI authenticationEndpoint) {

        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.authenticationEndpoint = authenticationEndpoint;
    }

    /**
     * Build the URI to use
     * @param state the pre-generated anti-forgery state
     * @return the URI
     */
    public URI build(final String state) {
        LOG.debug("Starting Google Authentication for Client ID {} and State {}", clientId, state);
        URI resultUri = UriComponentsBuilder.fromUri(authenticationEndpoint)
            .queryParam(CLIENT_ID_PARAM, clientId)
            .queryParam(RESPONSE_TYPE_PARAM, RESPONSE_TYPE_CODE)
            .queryParam(SCOPE_PARAM, SCOPES)
            .queryParam(REDIRECT_URI_PARAM, this.redirectUri.toString())
            .queryParam(STATE_PARAM, state)
            .build()
            .toUri();

        LOG.debug("Redirecting user to {}", resultUri);
        return resultUri;
    }
}
