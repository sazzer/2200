package uk.co.grahamcox.dirt.webapp.authentication;

import uk.co.grahamcox.dirt.authentication.AccessToken;

import java.util.Optional;

/**
 * Token to return when a user has just been authenticated.
 * Includes the access token details and the minimal user details
 */
public class UserToken {
    /** The access token */
    private final AccessToken accessToken;

    /** The name of the user */
    private final Optional<String> name;

    /**
     * Construct the user token
     * @param accessToken the access token
     * @param name the users name
     */
    public UserToken(final AccessToken accessToken, final Optional<String> name) {
        this.accessToken = accessToken;
        this.name = name;
    }

    /**
     * Get the access token
     * @return the access token
     */
    public AccessToken getAccessToken() {
        return accessToken;
    }

    /**
     * Get the users name
     * @return the users name
     */
    public Optional<String> getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserToken{" +
            "accessToken=" + accessToken +
            ", name='" + name + '\'' +
            '}';
    }
}
