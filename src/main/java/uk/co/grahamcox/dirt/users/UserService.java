package uk.co.grahamcox.dirt.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Service for managing users
 */
public class UserService implements UserLoader {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    /** {@inheritDoc} */
    @Override
    public Optional<User> loadUser(final ExternalUserId userId) {
        LOG.debug("Loading the user details for {}", userId);
        return Optional.empty();
    }
}
