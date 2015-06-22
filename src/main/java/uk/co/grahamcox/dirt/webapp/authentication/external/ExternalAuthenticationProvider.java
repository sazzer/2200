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
package uk.co.grahamcox.dirt.webapp.authentication.external;

import java.util.Map;

/**
 * Interface describing a provider that can be used for External Authentication
 */
public interface ExternalAuthenticationProvider {
    /**
     * Request that authentication be started with the given provider
     * @return the request to pass on to the browser for authentication
     */
    ExternalAuthenticationRequest requestAuthentication();

    /**
     * Complete authentication, using the given parameters for the authentication provider
     * @param params the parameters to complete authentication with
     * @return the result of completing authentication
     */
    AuthenticationResponse completeAuthentication(final Map<String, String> params);
}
