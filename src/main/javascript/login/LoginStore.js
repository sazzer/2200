import Reflux from "reflux";
import {LoginActions} from "login/LoginActions";

/**
 * Reflux Store for managing requests to log in
 */
export const LoginStore = Reflux.createStore({
    listenables: [LoginActions],
    /**
     * Handle a request to log in
     * @param {String} username The username to log in with
     * @param {String} password The password to log in with
     */
    onLogin: function(username, password) {
        console.log("Reflux Logging in with username: " + username + " and password: " + password);
        if (username === "graham@grahamcox.co.uk") {
            if (password === "password") {
                LoginActions.login.completed({
                    result: "SUCCESS",
                    userId: "12345"
                });
            } else {
                LoginActions.login.failed({
                    result: "INVALID_PASSWORD"
                });
            }
        } else {
            LoginActions.login.failed({
                result: "UNKNOWN_USER"
            });
        }
    }
});
