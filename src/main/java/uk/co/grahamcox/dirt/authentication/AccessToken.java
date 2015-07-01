package uk.co.grahamcox.dirt.authentication;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representation of an Access Token used to access secured resources
 */
public class AccessToken {
    /** The actual value of the access token */
    private final String value;

    /** The date/time that the access token expires at */
    private final ZonedDateTime expires;

    /**
     * Construct the access token
     * @param value the value of the access token
     * @param expires the expiry time of the access token
     */
    public AccessToken(final String value, final ZonedDateTime expires) {
        this.value = value;
        this.expires = expires;
    }

    /**
     * Get the value of the access token
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the time the access token expires
     * @return the expiry time
     */
    public ZonedDateTime getExpires() {
        return expires;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AccessToken that = (AccessToken) o;
        return Objects.equals(value, that.value) &&
            Objects.equals(expires, that.expires);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(value, expires);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "AccessToken{" +
            "value='" + value + '\'' +
            ", expires=" + expires +
            '}';
    }
}
