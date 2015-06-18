package uk.co.grahamcox.dirt.authentication;

/**
 * Exception thrown by the Authentication System
 */
public class AuthenticationException extends Exception {
    /**
     * Enumeration of the possible error codes
     */
    public enum ErrorCode {
        /** The requested user was unknown */
        UNKNOWN_USER,
        /** The password was incorrect */
        INVALID_PASSWORD,
        /** The user was disabled */
        DISABLED_USER,
        /** Some other internal error occurred */
        INTERNAL_ERROR
    }

    /** The underlying error code */
    private final ErrorCode errorCode;

    /**
     * Construct the exception
     * @param errorCode the error code
     */
    public AuthenticationException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Construct the exception
     * @param errorCode the error code
     * @param message an additional error message
     */
    public AuthenticationException(final ErrorCode errorCode, final String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Construct the exception
     * @param errorCode the error code
     * @param cause an additional root cause
     */
    public AuthenticationException(final ErrorCode errorCode, final Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * Construct the exception
     * @param cause the root cause
     */
    public AuthenticationException(final Throwable cause) {
        this(ErrorCode.INTERNAL_ERROR, cause);
    }

    /**
     * Get the error code
     * @return the error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
