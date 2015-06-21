import React from "react";
import Reflux from "reflux";
import {SocialLoginButton} from "ui/home/login/SocialLoginButton";
import {ExternalProvidersStore} from "login/ExternalProvidersStore";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    mixins: [Reflux.connect(ExternalProvidersStore, "providers")],

    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const buttons = this.state.providers.map((provider) =>
            <SocialLoginButton type={provider} onClick={this.onClickSocialButton} />);

      return <div>{buttons}</div>;
    },

    /**
     * Handle when a Social Login button was clicked
     * @param {String} type the type of button clicked
     */
    onClickSocialButton: function(type) {
        console.log("Clicked social button: " + type);
    }
});
