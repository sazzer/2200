package uk.co.grahamcox.dirt.users;

import java.util.UUID;
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
        User user = new User();
        user.addExternalUserId(userId);
        user.setUserId(new UserId(UUID.randomUUID().toString()));
        user.setName("Graham Cox");
        user.setEmail("graham@grahamcox.co.uk");
        return Optional.of(user);
    }
}
