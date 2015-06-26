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
        console.log("Rendering Login Form: ", this.state.providers);
        const buttons = this.state.providers.map((provider) =>
            <SocialLoginButton type={provider} />);

      return <div>{buttons}</div>;
    }
});
