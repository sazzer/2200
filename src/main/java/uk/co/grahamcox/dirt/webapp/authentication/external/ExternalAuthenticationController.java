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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to support using External Authentication Providers
 */
@Controller
@RequestMapping("/api/authentication/external")
public class ExternalAuthenticationController {
    /** The map of providers to use */
    private final Map<String, ExternalAuthenticationProvider> providers;

    /**
     * Construct the controller
     * @param providers the map of providers to use
     */
    public ExternalAuthenticationController(final Map<String, ExternalAuthenticationProvider> providers) {
        this.providers = providers;
    }

    /**
     * Get the list of supported providers
     * @return the list of supported providers
     */
    @RequestMapping
    @ResponseBody
    public List<String> getAuthenticationProviders() {
        List<String> providerNames = new ArrayList<>(providers.keySet());
        Collections.sort(providerNames);
        return providerNames;
    }
}
