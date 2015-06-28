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
package uk.co.grahamcox.dirt.authentication.external;

import java.net.URI;

/**
 * Details of the request to pass on to the browser to make for External Authentication
 */
public class ExternalAuthenticationRequest {
    /** The token to keep hold of for anti-forgery means */
    private final String antiForgeryToken;

    /** The URI to send the browser to for authentication purposes */
    private final URI redirectUri;

    /**
     * Construct the request
     * @param antiForgeryToken the anti-forgery token
     * @param redirectUri the redirect URI
     */
    public ExternalAuthenticationRequest(final String antiForgeryToken, final URI redirectUri) {
        this.antiForgeryToken = antiForgeryToken;
        this.redirectUri = redirectUri;
    }

    /**
     * Get the Anti-Forgery token
     * @return the Anti-Forgery token
     */
    public String getAntiForgeryToken() {
        return antiForgeryToken;
    }

    /**
     * Get the Redirect URI
     * @return the Redirect URI
     */
    public URI getRedirectUri() {
        return redirectUri;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ExternalAuthenticationRequest{" +
            "antiForgeryToken='" + antiForgeryToken + '\'' +
            ", redirectUri=" + redirectUri +
            '}';
    }
}
