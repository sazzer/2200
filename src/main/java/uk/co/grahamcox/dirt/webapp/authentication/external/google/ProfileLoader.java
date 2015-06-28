package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Mechanism to load the Google+ Profile for a user that's just logged in
 */
public class ProfileLoader {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(GoogleExternalAuthenticationProvider.class);

    /** The Profile Endpoint to use */
    private final URI profileEndpoint;

    /** The rest template to use */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Construct the Profile Loader
     * @param profileEndpoint the Google+ Profile Endpoint to use
     */
    public ProfileLoader(final URI profileEndpoint) {
        this.profileEndpoint = profileEndpoint;
    }

    /**
     * Load the User Profile for the given access token
     * @param accessToken the Google Access Token
     * @return the user profile
     */
    public ProfileResponse loadProfile(final String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        ResponseEntity<ProfileResponse> profile = restTemplate.exchange(new RequestEntity<>(httpHeaders,
                HttpMethod.GET,
                profileEndpoint),
            ProfileResponse.class);
        LOG.debug("Profile: {}", profile);
        return profile.getBody();
    }
}
