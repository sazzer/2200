package uk.co.grahamcox.dirt.webapp.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    /** Map of Authentication Error Codes to HTTP Statuses */
    private static final Map<AuthenticationException.ErrorCode, HttpStatus> ERROR_STATUSES = new HashMap<>();

    static {
        ERROR_STATUSES.put(AuthenticationException.ErrorCode.DISABLED_USER, HttpStatus.UNAUTHORIZED);
        ERROR_STATUSES.put(AuthenticationException.ErrorCode.UNKNOWN_USER, HttpStatus.NOT_FOUND);
        ERROR_STATUSES.put(AuthenticationException.ErrorCode.INVALID_PASSWORD, HttpStatus.UNAUTHORIZED);
        ERROR_STATUSES.put(AuthenticationException.ErrorCode.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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
     * Handle when an Authentication Exception occurs
     * @param e the exception to handle
     * @return the response from handling the exception
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationError(final AuthenticationException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getErrorCode().name());
        response.put("message", e.getMessage());

        HttpStatus statusCode = Optional.ofNullable(ERROR_STATUSES.get(e.getErrorCode()))
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, statusCode);
    }

    /**
     * Actually make a request to log in to the system
     * @param username the username to log in as
     * @param password the password to log in with
     * @return the authentication token
     * @throws AuthenticationException if an authentication error occurs
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AuthenticationToken login(@RequestParam(value = "username", required = true) final String username,
        @RequestParam(value = "password", required = true) final String password)
        throws AuthenticationException {

        return authenticationSystem.authenticate(new AuthenticationCredentials(username, password));
    }
}
