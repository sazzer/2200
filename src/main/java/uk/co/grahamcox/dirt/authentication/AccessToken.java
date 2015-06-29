package uk.co.grahamcox.dirt.authentication;

import java.util.Objects;

/**
 * Representation of an Access Token used to access secured resources
 */
public class AccessToken {
    /** The actual value of the access token */
    private final String value;

    /**
     * Construct the access token
     * @param value the value of the access token
     */
    public AccessToken(final String value) {
        this.value = value;
    }

    /**
     * Get the value of the access token
     * @return the value
     */
    public String getValue() {
        return value;
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
        AccessToken that = (AccessToken) o;
        return Objects.equals(value, that.value);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessToken{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
