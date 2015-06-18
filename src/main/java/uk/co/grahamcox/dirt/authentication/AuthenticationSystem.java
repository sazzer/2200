package uk.co.grahamcox.dirt.authentication;

import uk.co.grahamcox.dirt.user.User;
import uk.co.grahamcox.dirt.user.UserSystem;

import java.util.Optional;

/**
 * Internal system for managing authentication
 */
public class AuthenticationSystem {
    /** The User System to work with */
    private final UserSystem userSystem;

    /**
     * Construct the Authentication system
     * @param userSystem the user system
     */
    public AuthenticationSystem(final UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    /**
     * Actually authenticate the given user
     * @param credentials the credentials to log in with
     * @return the token if authentication is successful
     * @throws AuthenticationException if an error occurs authenticating the user
     */
    public AuthenticationToken authenticate(final AuthenticationCredentials credentials)
        throws AuthenticationException {
        Optional<User> user = userSystem.getByEmailAddress(credentials.getUsername());

        if (user.isPresent()) {
            User rawUser = user.get();
            if (!rawUser.getPassword().equals(credentials.getPassword())) {
                throw new AuthenticationException(AuthenticationException.ErrorCode.INVALID_PASSWORD);
            }

            return new AuthenticationToken(rawUser.getUserId().getId());
        } else {
            throw new AuthenticationException(AuthenticationException.ErrorCode.UNKNOWN_USER);
        }
    }
}
