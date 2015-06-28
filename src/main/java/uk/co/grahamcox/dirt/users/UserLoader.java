package uk.co.grahamcox.dirt.users;

import java.util.Optional;

/**
 * Mechanism to load user details by a variety of means
 */
public interface UserLoader {
    /**
     * Load the user details of the user represented by the given external authentication provider
     * @param userId the User ID as provided by the external provider
     * @return the user details, if they are present
     */
    Optional<User> loadUser(final ExternalUserId userId);
}
