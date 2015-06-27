import Reflux from "reflux";
import debug from "visionmedia-debug";
import {request} from "request";
import {LoginActions} from "login/LoginActions";

const log = debug("login:ExternalProvidersStore");

window.continueAuthentication = LoginActions.continueExternalLogin;

/**
 * Reflux Store for managing the list of available external providers
 */
export const ExternalProvidersStore = Reflux.createStore({
    listenables: [LoginActions],

    /**
     * Request the list of supported providers
     */
    init: function() {
        this.providers = [];

        request("/api/authentication/external")
            .then(response => {
                log("Received list of external providers: ", response);
                this.providers = response.data;
                this.trigger(this.providers);
            }).catch(error => {
                console.log("Error getting list of external providers: ", error);
            });
    },

    /**
     * Get the initial list of external providers
     * @return {Array} the initial list of external providers
     */
    getInitialState: function() {
        return this.providers;
    },

    /**
     * Continue to externally authenticate a user, handling the callback from the
     * external authentication provider
     * @param {String} provider The provider to use
     * @param {Object} params The parameters the provider gave use
     */
    onContinueExternalLogin: function(provider, params) {
        log("Continuing Authentication with provider: ", provider);
        request("/api/authentication/external/complete/" + provider, {
            method: "POST",
            data: params
        })
        .then(log)
        .catch(log);
    }
});
