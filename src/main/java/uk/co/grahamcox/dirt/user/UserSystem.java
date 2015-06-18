package uk.co.grahamcox.dirt.user;

import java.util.Optional;

/**
 * Internal System for managing Users
 */
public class UserSystem {
    /**
     * Get the user with the given Email Address
     * @param emailAddress the email address
     * @return the user, if there is one
     */
    public Optional<User> getByEmailAddress(final String emailAddress) {
        return Optional.empty();
    }

    /**
     * Get the user with the given User ID
     * @param userId the User ID
     * @return the user, if there is one
     */
    public Optional<User> getById(final UserId userId) {
        return Optional.empty();
    }
}
