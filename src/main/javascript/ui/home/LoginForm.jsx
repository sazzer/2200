import React from "react";
import ReactIntl from "react-intl";
import {LoginActions} from "login/LoginActions";
import {Alert} from "ui/Bootstrap/Alert";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    /**
     * Get the initial state of this component
     * @return {Object} the initial state
     */
    getInitialState: function() {
        return {
            username: "",
            password: "",
            submitted: false,
            register: false,
            error: null
        };
    },

    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const submitted = this.state.submitted;
        const username = this.state.username;
        const password = this.state.password;

        let usernameClasses = ["form-group"];
        let passwordClasses = ["form-group"];
        let usernameFeedback = [];
        let passwordFeedback = [];
        let errorMessages = [];

        if (submitted) {
            let missingField = false;

            if (username === "") {
                usernameClasses.push("has-error");
                usernameClasses.push("has-feedback");
                usernameFeedback = [
                    <span className="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>,
                    <span id="usernameStatus" className="sr-only">(error)</span>
                ];
                missingField = true;
            }
            if (password === "") {
                passwordClasses.push("has-error");
                passwordClasses.push("has-feedback");
                passwordFeedback = [
                    <span className="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>,
                    <span id="usernameStatus" className="sr-only">(error)</span>
                ];
                missingField = true;
            }

            if (missingField) {
                errorMessages.push(<Alert>{this.getIntlMessage("page.LoginForm.error.missingFields")}</Alert>);
            }
        }

        if (this.state.error) {
            const errorMessageKey = "page.LoginForm.error.loginErrors." + this.state.error.result;
            errorMessages.push(<Alert>{this.getIntlMessage(errorMessageKey)}</Alert>);
        }

        return <form className="test-loginform" action="#" onSubmit={this.onSubmitForm}>
            <div className={usernameClasses.join(" ")}>
                <label className="control-label" for="username">{this.getIntlMessage("page.LoginForm.username.label")}</label>
                <input type="email" className="test-username form-control" name="username" placeholder={this.getIntlMessage("page.LoginForm.username.placeholder")} aria-describedby="usernameStatus" value={this.state.username} onChange={this.onUsernameChange} />
                {usernameFeedback}
            </div>
            <div className={passwordClasses.join(" ")}>
                <label className="control-label" for="password">{this.getIntlMessage("page.LoginForm.password.label")}</label>
                <input type="password" className="test-password form-control" name="password" placeholder={this.getIntlMessage("page.LoginForm.password.placeholder")} aria-describedby="passwordStatus" value={this.state.password} onChange={this.onPasswordChange} />
                {passwordFeedback}
            </div>
            <button type="submit" className="test-loginbutton btn btn-lg btn-primary btn-block">{this.getIntlMessage("page.LoginForm.buttons.login")}</button>
            {errorMessages}
          </form>;
    },

    /**
     * Store the entered value for the username
     * @param {Event} e the event
     * @private
     */
    onUsernameChange: function(e) {
        this.setState({"username": e.target.value});
    },

    /**
     * Store the entered value for the password
     * @param {Event} e the event
     * @private
     */
    onPasswordChange: function(e) {
        this.setState({"password": e.target.value});
    },

    /**
     * Handle the form being submitted, and thus the user trying to log in
     * @return {bool} false so that the form isn't really submitted
     * @private
     */
    onSubmitForm: function() {
        const username = this.state.username;
        const password = this.state.password;
        this.setState({submitted: true});
        if (username !== "" && password !== "") {
            LoginActions.login.triggerPromise(username, password)
                .catch((error) => {
                    if (error.result === "UNKNOWN_USER") {
                        this.setState({
                            register: true,
                            error: null
                        });
                    } else {
                        this.setState({
                            register: false,
                            error
                        });
                    }
                });
        }
        return false;
    }
});
