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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to support using External Authentication Providers
 */
@Controller
@RequestMapping("/api/authentication/external")
public class ExternalAuthenticationController {
    /** The map of providers to use */
    private final Map<String, Optional<ExternalAuthenticationProvider>> providers;

    /**
     * Construct the controller
     * @param providers the map of providers to use
     */
    public ExternalAuthenticationController(final Map<String, Optional<ExternalAuthenticationProvider>> providers) {
        this.providers = providers;
    }

    /**
     * Get the list of supported providers
     * @return the list of supported providers
     */
    @RequestMapping
    @ResponseBody
    public List<String> getAuthenticationProviders() {
        return providers.entrySet().stream()
            .filter(entry -> entry.getValue().isPresent())
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Request that we authenticate with the specified authentication provider
     * @param providerName the provider to use
     * @return the response entity
     */
    @RequestMapping("/request/{provider}")
    @ResponseBody
    public ResponseEntity<ExternalAuthenticationRequest> requestExternalAuthentication(
        @PathVariable("provider") final String providerName) {

        return Optional.ofNullable(providers.get(providerName))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(ExternalAuthenticationProvider::requestAuthentication)
            .map(authenticationRequest -> new ResponseEntity<>(authenticationRequest, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handle the callback from finishing external authentication
     * @param providerName the provider to use
     * @return the view for the callback page
     */
    @RequestMapping("/callback/{provider}")
    public ModelAndView callbackExternalAuthentication(
        @PathVariable("provider") final String providerName) {
        ModelAndView result = new ModelAndView("/externalAuthenticationCallback");
        result.addObject("provider", providerName);
        return result;
    }
}
