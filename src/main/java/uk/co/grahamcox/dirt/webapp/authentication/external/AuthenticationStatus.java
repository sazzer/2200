package uk.co.grahamcox.dirt.webapp.authentication.external;

/**
 * Enumeration of the possible statuses of authentication
 */
public enum AuthenticationStatus {
    /** The authentication was a success */
    SUCCESS,

    /** The authentication was a success, but the user was previously unknown to the system */
    NEW_USER,

    /** The authentication was a failure */
    ERROR
}
