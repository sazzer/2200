package uk.co.grahamcox.dirt.webapp.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.co.grahamcox.dirt.authentication.AuthenticationCredentials;
import uk.co.grahamcox.dirt.authentication.AuthenticationException;
import uk.co.grahamcox.dirt.authentication.AuthenticationSystem;
import uk.co.grahamcox.dirt.authentication.AuthenticationToken;

/**
 * Controller to handle a request to log in
 */
@Controller
@RequestMapping(value = "/api/authentication/login")
public class LoginController {
    /** The authentication system to use */
    private final AuthenticationSystem authenticationSystem;

    /**
     * Construct the login controller
     * @param authenticationSystem the authentication system
     */
    public LoginController(final AuthenticationSystem authenticationSystem) {
        this.authenticationSystem = authenticationSystem;
    }

    /**
     * Actually make a request to log in to the system
     * @param username the username to log in as
     * @param password the password to log in with
     * @return the authentication token
     * @throws AuthenticationException if an authentication error occurs
     */
    @RequestMapping(method = RequestMethod.POST)
    public AuthenticationToken login(final String username, final String password)
        throws AuthenticationException {

        return authenticationSystem.authenticate(new AuthenticationCredentials(username, password));
    }
}
