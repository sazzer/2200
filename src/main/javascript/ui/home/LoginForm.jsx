import React from "react";
import ReactIntl from "react-intl";
import {LoginActions} from "login/LoginActions";
import {Alert} from "ui/Bootstrap/Alert";
import {Input} from "ui/Bootstrap/Form/Input";

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

        let errorMessages = [];

        if (submitted) {
            let missingField = false;

            if (username === "") {
                missingField = true;
            }
            if (password === "") {
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
            <Input name="username"
                label="page.LoginForm.username.label"
                placeholder="page.LoginForm.username.placeholder"
                type="email"
                error={submitted && username === ""}
                value={username}
                onChange={this.onUsernameChange} />
            <Input name="password"
                label="page.LoginForm.password.label"
                placeholder="page.LoginForm.password.placeholder"
                type="password"
                error={submitted && password === ""}
                value={password}
                onChange={this.onPasswordChange} />
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
        this.setState({
            username: e.target.value,
            submitted: false,
            error: null
        });
    },

    /**
     * Store the entered value for the password
     * @param {Event} e the event
     * @private
     */
    onPasswordChange: function(e) {
        this.setState({
            password: e.target.value,
            submitted: false,
            error: null
        });
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
