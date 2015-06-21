import Reflux from "reflux";
import {request} from "request";
/**
 * Reflux Store for managing the list of available external providers
 */
export const ExternalProvidersStore = Reflux.createStore({
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
    }
});
