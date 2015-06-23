package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response from Google containing the Access Token details
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponse {
    /** The Access Token */
    @JsonProperty("access_token")
    private String accessToken;

    /** The type of Access Token */
    @JsonProperty("token_type")
    private String tokenType;

    /** The expiry of the Access Token */
    @JsonProperty("expires_in")
    private Long expires;

    /**
     * Get the Access Token
     * @return the Access Token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Set the Access Token
     * @param accessToken the Access Token
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get the type of Access Token
     * @return the type of Access Token
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Set the type of Access Token
     * @param tokenType the type of Access Token
     */
    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Get the expiry of the Access Token
     * @return the expiry of the Access Token
     */
    public Long getExpires() {
        return expires;
    }

    /**
     * Set the expiry of the Access Token
     * @param expires the expiry of the Access Token
     */
    public void setExpires(final Long expires) {
        this.expires = expires;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessTokenResponse{");
        sb.append("accessToken='").append(accessToken).append('\'');
        sb.append(", tokenType='").append(tokenType).append('\'');
        sb.append(", expires=").append(expires);
        sb.append('}');
        return sb.toString();
    }
}
