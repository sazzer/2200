package uk.co.grahamcox.dirt.users;

import java.util.Objects;

/**
 * Representation of a User ID as supplied by an External Authentication provider
 */
public class ExternalUserId {
    /** The name of the provider */
    private final String provider;

    /** The ID as supplied by this provider */
    private final String id;

    /**
     * Construct the external user ID
     * @param provider the name of the provider
     * @param id the id supplied by the provider
     */
    public ExternalUserId(final String provider, final String id) {
        this.provider = provider;
        this.id = id;
    }

    /**
     * Get the provider
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Get the ID
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ExternalUserId{" +
            "provider='" + provider + '\'' +
            ", id='" + id + '\'' +
            '}';
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
        final ExternalUserId that = (ExternalUserId) o;
        return Objects.equals(provider, that.provider) &&
            Objects.equals(id, that.id);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(provider, id);
    }
}
