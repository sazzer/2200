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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import uk.co.grahamcox.dirt.authentication.AccessToken;
import uk.co.grahamcox.dirt.authentication.AccessTokenService;
import uk.co.grahamcox.dirt.authentication.external.AuthenticationResponse;
import uk.co.grahamcox.dirt.authentication.external.ExternalAuthenticationService;
import uk.co.grahamcox.dirt.users.ExternalUserId;
import uk.co.grahamcox.dirt.users.User;
import uk.co.grahamcox.dirt.users.UserService;

/**
 * Controller to support using External Authentication Providers
 */
@Controller
@RequestMapping("/api/authentication/external")
public class ExternalAuthenticationController {
    /** the logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(ExternalAuthenticationController.class);

    /** The service for external authentication */
    private final ExternalAuthenticationService externalAuthenticationService;

    /** The service for managing users */
    private final UserService userService;

    /** The service for working with access tokens */
    private final AccessTokenService accessTokenService;

    /**
     * Construct the controller
     * @param externalAuthenticationService the external authentication service
     * @param userService The User Service
     * @param accessTokenService the Access Token Service
     */
    public ExternalAuthenticationController(final ExternalAuthenticationService externalAuthenticationService,
        final UserService userService,
        final AccessTokenService accessTokenService) {
        this.externalAuthenticationService = externalAuthenticationService;
        this.userService = userService;
        this.accessTokenService = accessTokenService;
    }


    /**
     * Get the list of supported providers
     * @return the list of supported providers
     */
    @RequestMapping
    @ResponseBody
    public List<String> getAuthenticationProviders() {
        return externalAuthenticationService.getProviders();
    }

    /**
     * Request that we authenticate with the specified authentication provider
     * @param providerName the provider to use
     * @return the response entity
     */
    @RequestMapping("/request/{provider}")
    @ResponseBody
    public ResponseEntity<Object> requestExternalAuthentication(
        @PathVariable("provider") final String providerName) {

        LOG.debug("Request that we start external authentication with provider {}", providerName);
        ResponseEntity<Object> result = externalAuthenticationService.requestAuthentication(providerName)
            .map(authenticationRequest -> {
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(authenticationRequest.getRedirectUri());
                return new ResponseEntity<>(headers, HttpStatus.FOUND);
            })
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        LOG.debug("Request for external authentication via provider {}: {}", providerName, result);
        return result;
    }

    /**
     * Handle the callback from finishing external authentication
     * @param providerName the provider to use
     * @return the view for the callback page
     */
    @RequestMapping("/callback/{provider}")
    public ModelAndView callbackExternalAuthentication(@PathVariable("provider") final String providerName) {
        LOG.debug("Received callback from provider {}", providerName);
        ModelAndView result = new ModelAndView("/externalAuthenticationCallback");
        result.addObject("provider", providerName);
        return result;
    }

    /**
     * Finish external authentication, returning either a successful login, a new user login or a failure
     * @param providerName the name of the authentication provider
     * @param params the parameters from the authentication provider
     * @return the response from authenticating the user
     */
    @RequestMapping("/complete/{provider}")
    @ResponseBody
    public Optional<AccessToken> finishExternalAuthentication(@PathVariable("provider") final String providerName,
        @RequestBody final Map<String, String> params) {
        LOG.debug("Completing external authentication from provider {} with params {}", providerName, params);

        AuthenticationResponse authenticationResponse =
            externalAuthenticationService.completeAuthentication(providerName, params);
        Optional<User> user =
            userService.loadUser(new ExternalUserId(providerName, authenticationResponse.getProviderId()));
        AccessToken accessToken = accessTokenService.generate(user.get());

        return Optional.of(accessToken);
    }
}
