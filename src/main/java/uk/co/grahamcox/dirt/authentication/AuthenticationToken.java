package uk.co.grahamcox.dirt.authentication;

/**
 * Authentication Token to represent a logged in session
 */
public class AuthenticationToken {
    /** The actual authentication token */
    private final String token;

    /**
     * Construct the authentication token
     * @param token the token
     */
    public AuthenticationToken(final String token) {
        this.token = token;
    }

    /**
     * Get the token
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AuthenticationToken{" +
            "token='" + token + '\'' +
            '}';
    }
}
