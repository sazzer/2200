package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Details of an Email Address in the users Google Profile
 */
public class ProfileEmail {
    /** The actual email address */
    @JsonProperty("value")
    private String email;

    /** The type of email address */
    @JsonProperty("type")
    private String type;

    /**
     * Get the email address
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address
     * @param email the email address
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get the type of email address
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of email address
     * @param type the type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileEmail{");
        sb.append("email='").append(email).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
