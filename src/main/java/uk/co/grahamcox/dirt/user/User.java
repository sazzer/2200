package uk.co.grahamcox.dirt.user;

/**
 * Representation of a user
 */
public class User {
    /** The ID of the user */
    private final UserId userId;
    /** The email address of the user */
    private final String email;
    /** The password of the user */
    private final String password;

    /**
     * Construct the user
     * @param userId the ID of the user
     * @param email the email address of the user
     * @param password the password of the user
     */
    public User(final UserId userId, final String email, final String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    /**
     * Get the User ID
     * @return the User ID
     */
    public UserId getUserId() {
        return userId;
    }

    /**
     * Get the email address
     * @return the email address
     */
    public String getEmail() {
        return email;
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
        return "User{" +
            "userId=" + userId +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
