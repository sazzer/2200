import Reflux from "reflux";
import {LoginActions} from "login/LoginActions";
import {request} from "request";

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

        request("/api/authentication/login", {
            method: "POST",
            data: {
                username,
                password
            },
            dataType: "application/x-www-form-urlencoded"
        }).then((response) => {
            this.trigger({
                username,
                token: response.data.token
            });

            LoginActions.login.completed({
                result: "SUCCESS",
                userToken: response.data.token
            });
        }, (error) => {
            this.trigger({});

            LoginActions.login.failed({
                result: error.data.error,
                message: error.data.message
            });
        });
    }
});
