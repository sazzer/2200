package uk.co.grahamcox.dirt.authentication;

/**
 * Representation of the credentials to log in with
 */
public class AuthenticationCredentials {
    /** The username to log in with */
    private final String username;

    /** The password to log in with */
    private final String password;

    /**
     * Construct the credentials
     * @param username the username
     * @param password the password
     */
    public AuthenticationCredentials(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Get the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AuthenticationCredentials{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
