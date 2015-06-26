import React from "react";
import Reflux from "reflux";
import {SocialLoginButton} from "ui/home/login/SocialLoginButton";
import {ExternalProvidersStore} from "login/ExternalProvidersStore";
import {LoginActions} from "login/LoginActions";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    mixins: [Reflux.listenTo(ExternalProvidersStore, "onProvidersChange", "onProvidersChange")],

    /**
     * Get the initial state of the login form
     * @return {Object} The initial state
     */
    getInitialState: function() {
        return {
            providers: []
        };
    },

    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        console.log("Rendering Login Form: ", this.state.providers);
        const buttons = this.state.providers.map((provider) =>
            <SocialLoginButton type={provider} onClick={this.onClickSocialButton} />);

      return <div>{buttons}</div>;
    },

    /**
     * Handle when a Social Login button was clicked
     * @param {String} type the type of button clicked
     */
    onClickSocialButton: function(type) {
        LoginActions.startExternalLogin(type);
    },

    /**
     * Handle the fact that the list of providers has changed
     * @param {Array} providers the new list of providers
     */
    onProvidersChange: function(providers) {
        console.log("Providers list has changed", providers);
        this.setState({
            providers
        });
    }
});
