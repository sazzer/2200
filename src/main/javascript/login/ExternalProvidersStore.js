import Reflux from "reflux";

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
        setTimeout(() => {
            this.trigger([
                "twitter", "facebook"
                ]);
        }, 3000);
    }
});
