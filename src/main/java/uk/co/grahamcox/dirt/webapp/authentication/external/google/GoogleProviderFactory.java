package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationProvider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * Spring Factory Bean for the Google Authentication Provider
 */
public class GoogleProviderFactory extends AbstractFactoryBean<Optional<ExternalAuthenticationProvider>> {
    /** The Client ID to use */
    private final Optional<String> clientId;

    /** The Client Secret to use */
    private final Optional<String> clientSecret;

    /** The Redirect URI to use */
    private final Optional<URI> redirectUri;

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
     * @throws URISyntaxException if the Redirect URI is invalid
     */
    public GoogleProviderFactory(final String clientId,
         final String clientSecret,
         final String redirectUri,
         final URI authenticationEndpoint,
         final URI tokenEndpoint) throws URISyntaxException {

        this.clientId = Optional.ofNullable(clientId)
            .filter(v -> !v.isEmpty());
        this.clientSecret = Optional.ofNullable(clientSecret)
            .filter(v -> !v.isEmpty());

        Optional<String> redirectUriToUse = Optional.ofNullable(redirectUri)
            .filter(v -> !v.isEmpty());
        if (redirectUriToUse.isPresent()) {
            this.redirectUri = Optional.of(new URI(redirectUriToUse.get()));
        } else {
            this.redirectUri = Optional.empty();
        }
        this.authenticationEndpoint = authenticationEndpoint;
        this.tokenEndpoint = tokenEndpoint;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType() {
        return Optional.class;
    }

    /**
     * Actually construct the authentication provider. This will return an actual instance if it's
     * correctly configured, or Optional.empty if not
     * @return the authentication provider
     * @throws Exception if an error occurs
     */
    @Override
    protected Optional<ExternalAuthenticationProvider> createInstance() throws Exception {
        Optional<ExternalAuthenticationProvider> result;
        if (clientId.isPresent() && clientSecret.isPresent() && redirectUri.isPresent()) {
            result = Optional.of(new GoogleExternalAuthenticationProvider(
                clientId.get(),
                clientSecret.get(),
                redirectUri.get(),
                authenticationEndpoint,
                tokenEndpoint
            ));
        } else {
            result = Optional.empty();
        }

        return result;
    }
}
