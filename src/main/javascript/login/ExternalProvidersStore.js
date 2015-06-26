import Reflux from "reflux";
import {request} from "request";
import {LoginActions} from "login/LoginActions";

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
                console.log("Received list of external providers: ", response);
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
     * Perform an External Login using the named provider
     * @param {String} provider the provider to use
     */
    onStartExternalLogin: function(provider) {
        console.log("Starting external login with provider: ", provider);
        request("/api/authentication/external/request/" + provider)
            .then((req) => {
                window.open(req.data.redirectUri,
                    "externalAuthentication",
                    "menubar=no,location=no,toolbar=no,dependent=yes");
            })
            .catch(console.error);
    },

    /**
     * Continue to externally authenticate a user, handling the callback from the
     * external authentication provider
     * @param {String} provider The provider to use
     * @param {Object} params The parameters the provider gave use
     */
    onContinueExternalLogin: function(provider, params) {
        console.log("Continuing Authentication with provider: ", provider);
        request("/api/authentication/external/complete/" + provider, {
            method: "POST",
            data: params
        })
        .then(console.log)
        .catch(console.error);
    }
});
