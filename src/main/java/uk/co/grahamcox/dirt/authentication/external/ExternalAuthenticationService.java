package uk.co.grahamcox.dirt.authentication.external;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for providing authentication using external services
 */
public class ExternalAuthenticationService {
    /** the logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(ExternalAuthenticationService.class);

    /** The map of providers to use */
    private final Map<String, Optional<ExternalAuthenticationProvider>> providers;

    /**
     * Construct the service
     * @param providers the map of providers
     */
    public ExternalAuthenticationService(final Map<String, Optional<ExternalAuthenticationProvider>> providers) {
        this.providers = providers;
    }

    /**
     * Get the list of provider names that can be used
     * @return the list of provider names
     */
    public List<String> getProviders() {
        LOG.trace("Getting the list of external authentication providers");

        return providers.entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Request that authentication be started with the given provider
     * @param providerName the name of the provider to use
     * @return the request to pass on to the browser for authentication
     */
    public Optional<ExternalAuthenticationRequest> requestAuthentication(final String providerName) {
        LOG.trace("Starting external authentication using provider {}", providerName);

        Optional<ExternalAuthenticationRequest> request = Optional.ofNullable(providers.get(providerName))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(ExternalAuthenticationProvider::requestAuthentication);

        LOG.debug("External authentication request for provider {}: {}", providerName, request);
        return request;
    }

    /**
     * Complete authentication, using the given parameters for the authentication provider
     * @param providerName the name of the provider to use
     * @param params the parameters to complete authentication with
     * @return the result of completing authentication
     */
    public AuthenticationResponse completeAuthentication(final String providerName,
        final Map<String, String> params) {
        LOG.trace("Completing external authentication using provider {} and params {}", providerName, params);
        return Optional.ofNullable(providers.get(providerName))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(provider -> provider.completeAuthentication(params))
            .orElseThrow(UnsupportedOperationException::new);
    }
}
