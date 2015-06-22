import Reflux from "reflux";
import {request} from "request";
import {LoginActions} from "login/LoginActions";

window.continueAuthentication = (p1, p2) => {
    console.log("Continuing Authentication with provider: " + p1);
    console.log(p2);
};

/**
 * Reflux Store for managing the list of available external providers
 */
export const ExternalProvidersStore = Reflux.createStore({
    listenables: [LoginActions],

    /**
     * Get the initial list of external providers
     * @return {Array} the initial list of external providers
     */
    getInitialState: function() {
        return [];
    },

    /**
     * Request the list of supported providers
     */
    init: function() {
        request("/api/authentication/external")
            .then(response => {
                this.trigger(response.data);
            });
    },

    /**
     * Perform an External Login using the named provider
     * @param {String} provider the provider to use
     */
    onExternalLogin: function(provider) {
        console.log("Starting external login with provider: " + provider);
        request("/api/authentication/external/request/" + provider)
            .then((req) => {
                window.open(req.data.redirectUri,
                    "externalAuthentication",
                    "menubar=no,location=no,toolbar=no,dependent=yes");
            })
            .catch(console.error);
    }
});
