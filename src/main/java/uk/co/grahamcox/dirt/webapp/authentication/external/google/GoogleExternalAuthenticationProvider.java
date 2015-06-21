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
package uk.co.grahamcox.dirt.webapp.authentication.external.google;

import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationProvider;
import uk.co.grahamcox.dirt.webapp.authentication.external.ExternalAuthenticationRequest;

/**
 * External Authentication Provider for logging in with Google
 */
public class GoogleExternalAuthenticationProvider implements ExternalAuthenticationProvider {
    /**
     * Check whether this provider is enabled or not
     *
     * @return true if the provider is enabled. False if not
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Request that authentication be started with the given provider
     *
     * @return the request to pass on to the browser for authentication
     */
    @Override
    public ExternalAuthenticationRequest requestAuthentication() {
        return null;
    }
}
