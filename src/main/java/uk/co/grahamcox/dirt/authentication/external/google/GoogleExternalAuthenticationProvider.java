/*
 * Copyright (C) 21/06/15 graham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.grahamcox.dirt.authentication.external.google;

import uk.co.grahamcox.dirt.authentication.external.AuthenticationResponse;
import uk.co.grahamcox.dirt.authentication.external.ExternalAuthenticationProvider;
import uk.co.grahamcox.dirt.authentication.external.ExternalAuthenticationRequest;

import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * External Authentication Provider for logging in with Google
 */
public class GoogleExternalAuthenticationProvider implements ExternalAuthenticationProvider {
    /** The mechanism to build an Authentication URI */
    private final AuthentictionUriBuilder authentictionUriBuilder;

    /** The mechanism to load an access token */
    private final AccessTokenLoader accessTokenLoader;

    /** The mechanism to load the users profile */
    private final ProfileLoader profileLoader;

    /**
     * Construct the authentication provider
     * @param authentictionUriBuilder the Authentication URI Builder
     * @param accessTokenLoader the Access Token Loader
     * @param profileLoader the Profile Loader
     */
    public GoogleExternalAuthenticationProvider(final AuthentictionUriBuilder authentictionUriBuilder,
        final AccessTokenLoader accessTokenLoader,
        final ProfileLoader profileLoader) {

        this.authentictionUriBuilder = authentictionUriBuilder;
        this.accessTokenLoader = accessTokenLoader;
        this.profileLoader = profileLoader;
    }

    /**
     * Request that authentication be started with the given provider
     *
     * @return the request to pass on to the browser for authentication
     */
    @Override
    public ExternalAuthenticationRequest requestAuthentication() {
        String state = UUID.randomUUID().toString();

        URI resultUri = authentictionUriBuilder.build(state);

        return new ExternalAuthenticationRequest(state, resultUri);
    }

    /**
     * Complete authentication, using the given parameters for the authentication provider
     *
     * @param params the parameters to complete authentication with
     * @return the result of completing authentication
     */
    @Override
    public AuthenticationResponse completeAuthentication(final Map<String, String> params) {
        String accessToken = accessTokenLoader.loadAccessToken(params.get("code"));
        ProfileResponse profileResponse = profileLoader.loadProfile(accessToken);
        Optional<String> name = Optional.ofNullable(profileResponse.getName());
        Optional<String> email = profileResponse.getEmails().stream()
            .filter(v -> "account".equals(v.getType()))
            .map(ProfileEmail::getEmail)
            .findFirst();

        return new AuthenticationResponse(profileResponse.getId(),
            name,
            email);
    }
}
