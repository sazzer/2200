package uk.co.grahamcox.dirt.authentication;

import uk.co.grahamcox.dirt.users.User;

/**
 * Service for working with Access Tokens
 */
public class AccessTokenService {
    /**
     * Generate an Access Token for the given User
     * @param user the User to generate an Access Token for
     * @return the Access Token
     */
    public AccessToken generate(final User user) {
        return new AccessToken(user.getUserId().get().getId());
    }
}
