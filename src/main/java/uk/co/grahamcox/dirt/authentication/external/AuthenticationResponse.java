package uk.co.grahamcox.dirt.authentication.external;

/**
 * Response from authenticating with an external provider
 */
public class AuthenticationResponse {
    /** The authentication token to use for future requests */
    private final String authenticationToken;

    /** The status of the authentication */
    private final AuthenticationStatus status;

    /**
     * Construct the authentication response
     * @param authenticationToken the authentication token to use
     * @param status the authentication status
     */
    public AuthenticationResponse(final String authenticationToken, final AuthenticationStatus status) {
        this.authenticationToken = authenticationToken;
        this.status = status;
    }

    /**
     * Get the authentication token
     * @return the authentication token
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Get the authentication status
     * @return the authentication status
     */
    public AuthenticationStatus getStatus() {
        return status;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticationResponse{");
        sb.append("authenticationToken='").append(authenticationToken).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
