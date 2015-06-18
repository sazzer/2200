package uk.co.grahamcox.dirt.user;

import java.util.Objects;

/**
 * Representation of the ID of a user
 */
public class UserId {
    /** The ID of the user */
    private final String id;

    /**
     * Construct the User ID
     * @param id the User ID
     */
    public UserId(final String id) {
        this.id = id;
    }

    /**
     * Get the User ID
     * @return the User ID
     */
    public String getId() {
        return id;
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
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserId{" +
            "id='" + id + '\'' +
            '}';
    }
}
