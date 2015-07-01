package uk.co.grahamcox.dirt.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import uk.co.grahamcox.dirt.users.User;

import java.security.Key;
import java.time.Clock;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Service for working with Access Tokens
 */
public class AccessTokenService {
    /** The key to use for signing the JWT */
    private final Key key = MacProvider.generateKey();

    /** The clock to use */
    private final Clock clock;

    /**
     * Construct the service
     * @param clock the clock to use
     */
    public AccessTokenService(final Clock clock) {
        this.clock = clock;
    }

    /**
     * Generate an Access Token for the given User
     * @param user the User to generate an Access Token for
     * @return the Access Token
     */
    public AccessToken generate(final User user) {
        user.getUserId().orElseThrow(() ->
            new IllegalArgumentException("Unable to generate Access Token for a User without a User ID"));

        ZonedDateTime now = ZonedDateTime.now(clock);
        ZonedDateTime expires = now.plus(Period.parse("P1D"));

        String jwtAccessToken = Jwts.builder()
            .setIssuer("urn:grahamcox.co.uk:dirt:AccessTokenService")
            .setSubject(user.getUserId().get().getId())
            .setAudience("urn:grahamcox.co.uk:dirt:AccessTokenService")
            .setIssuedAt(Date.from(now.toInstant()))
            .setNotBefore(Date.from(now.toInstant()))
            .setExpiration(Date.from(expires.toInstant()))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();

        return new AccessToken(jwtAccessToken, expires);
    }
}
