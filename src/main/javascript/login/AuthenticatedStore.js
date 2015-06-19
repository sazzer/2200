import Reflux from "reflux";
import {LoginStore} from "login/LoginStore";

/**
 * Reflux Store for managing whether we're authenticated or not
 */
export const AuthenticatedStore = Reflux.createStore({
    /**
     * Set up listeners for when various actions happen.
     * Currently this just handles when we successfully log in to the system
     */
    init: function() {
        this.listenTo(LoginStore, this.onLogin);
    },

    /**
     * Handle the fact that we've just logged in to the system
     * @param {String} username The username that we logged in with
     * @param {String} token The authentication token that we were issued
     */
    onLogin: function({username, token}) {
        console.log("User " + username + " logged in with token " + token);
        this.trigger({
            username,
            token,
            authenticated: true
        });
    }
});
