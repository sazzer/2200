package uk.co.grahamcox.dirt.users;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Representation of a User profile
 */
public class User {
    /** The set of External IDs for the User */
    private final Set<ExternalUserId> externalUserIds = new HashSet<>();

    /** The ID of the user. Only present if the user has been persisted */
    private Optional<UserId> userId = Optional.empty();

    /** The Last Modified Date of the user. Only present if the user has been persisted */
    private Optional<ZonedDateTime> lastModifiedDate = Optional.empty();

    /** The name of the user */
    private Optional<String> name = Optional.empty();

    /** The email address of the user */
    private Optional<String> email = Optional.empty();

    /**
     * Get the User ID
     * @return the User ID
     */
    public Optional<UserId> getUserId() {
        return userId;
    }

    /**
     * Set the User ID
     * @param userId the User ID
     */
    public void setUserId(final Optional<UserId> userId) {
        this.userId = userId;
    }

    /**
     * Set the User ID to a definite value
     * @param userId the User ID
     */
    public void setUserId(final UserId userId) {
        setUserId(Optional.of(userId));
    }

    /**
     * Clear the User ID
     */
    public void clearUserId() {
        setUserId(Optional.empty());
    }

    /**
     * Get the Last Modified Date
     * @return the Last Modified Date
     */
    public Optional<ZonedDateTime> getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Set the Last Modified Date
     * @param lastModifiedDate the Last Modified Date
     */
    public void setLastModifiedDate(final Optional<ZonedDateTime> lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Set the Last Modified Date to a definite value
     * @param lastModifiedDate the Last Modified Date
     */
    public void setLastModifiedDate(final ZonedDateTime lastModifiedDate) {
        setLastModifiedDate(Optional.of(lastModifiedDate));
    }

    /**
     * Clear the Last Modified Date
     */
    public void clearLastModifiedDate() {
        setLastModifiedDate(Optional.empty());
    }

    /**
     * Get the Name
     * @return the Name
     */
    public Optional<String> getName() {
        return name;
    }

    /**
     * Set the Name
     * @param name the Name
     */
    public void setName(final Optional<String> name) {
        this.name = name;
    }

    /**
     * Set the Name to a definite value
     * @param name the Name
     */
    public void setName(final String name) {
        setName(Optional.of(name));
    }

    /**
     * Clear the Name
     */
    public void clearName() {
        setName(Optional.empty());
    }

    /**
     * Get the Email
     * @return the Email
     */
    public Optional<String> getEmail() {
        return email;
    }

    /**
     * Set the Email
     * @param email the Email
     */
    public void setEmail(final Optional<String> email) {
        this.email = email;
    }

    /**
     * Set the Email to a definite value
     * @param email the Email
     */
    public void setEmail(final String email) {
        setEmail(Optional.of(email));
    }

    /**
     * Clear the Email
     */
    public void clearEmail() {
        setEmail(Optional.empty());
    }

    /**
     * Get the External User IDs
     * @return the External User IDs
     */
    public Set<ExternalUserId> getExternalUserIds() {
        return Collections.unmodifiableSet(externalUserIds);
    }

    /**
     * Add a new External User ID
     * @param externalUserId the External User ID to add
     */
    public void addExternalUserId(final ExternalUserId externalUserId) {
        this.externalUserIds.add(externalUserId);
    }

    /**
     * Remove an External User ID
     * @param externalUserId the External User ID to remove
     */
    public void removeExternalUserId(final ExternalUserId externalUserId) {
        this.externalUserIds.remove(externalUserId);
    }

    /**
     * Clear the External User IDs
     */
    public void clearExternalUserIds() {
        this.externalUserIds.clear();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("externalUserIds=").append(externalUserIds);
        sb.append(", userId=").append(userId);
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}
