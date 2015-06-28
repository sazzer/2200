package uk.co.grahamcox.dirt.authentication.external;

import java.util.Optional;

/**
 * Response from authenticating with an external provider
 */
public class AuthenticationResponse {
    /** The ID of the user as assigned by the external provider */
    private final String providerId;

    /** The name of the user as known by the provider */
    private Optional<String> userName;

    /** The email address of the user as known by the provider */
    private Optional<String> userEmail;

    /**
     * Construct the response with all of the details
     * @param providerId the ID of the user
     * @param userName the name of the user
     * @param userEmail the email address of the user
     */
    public AuthenticationResponse(final String providerId,
                                  final Optional<String> userName,
                                  final Optional<String> userEmail) {

        this.providerId = providerId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    /**
     * Construct the response with only an ID
     * @param providerId the ID of the user
     */
    public AuthenticationResponse(final String providerId) {
        this(providerId, Optional.empty(), Optional.empty());
    }

    /**
     * Get the ID of the user
     * @return the ID of the user
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * Get the name of the user
     * @return the name of the user
     */
    public Optional<String> getUserName() {
        return userName;
    }

    /**
     * Set the name of the user
     * @param userName the name of the user
     */
    public void setUserName(final Optional<String> userName) {
        this.userName = userName;
    }

    /**
     * Get the email address of the user
     * @return the email address
     */
    public Optional<String> getUserEmail() {
        return userEmail;
    }

    /**
     * Set the email address of the user
     * @param userEmail the email address
     */
    public void setUserEmail(final Optional<String> userEmail) {
        this.userEmail = userEmail;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AuthenticationResponse{" +
            "providerId='" + providerId + '\'' +
            ", userName=" + userName +
            ", userEmail=" + userEmail +
            '}';
    }
}
