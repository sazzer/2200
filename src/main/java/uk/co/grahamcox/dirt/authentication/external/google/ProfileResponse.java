package uk.co.grahamcox.dirt.authentication.external.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Representation of the Google Profile for a user
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileResponse {
    /** The User ID  */
    @JsonProperty("id")
    private String id;

    /** The email addresses */
    @JsonProperty("emails")
    private List<ProfileEmail> emails;

    /** The name of the user */
    @JsonProperty("displayName")
    private String name;

    /**
     * Get the User ID
     * @return the User ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set the User ID
     * @param id the User ID
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Get the list of email addresses
     * @return the email addressess
     */
    public List<ProfileEmail> getEmails() {
        return emails;
    }

    /**
     * Set the list of email addresses
     * @param emails the email addresses
     */
    public void setEmails(final List<ProfileEmail> emails) {
        this.emails = emails;
    }

    /**
     * Get the user name
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user name
     * @param name the user name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileResponse{");
        sb.append("id='").append(id).append('\'');
        sb.append(", emails=").append(emails);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
