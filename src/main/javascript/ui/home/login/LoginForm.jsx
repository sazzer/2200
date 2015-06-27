import React from "react";
import Reflux from "reflux";
import debug from "visionmedia-debug";
import {SocialLoginButton} from "ui/home/login/SocialLoginButton";
import {ExternalProvidersStore} from "login/ExternalProvidersStore";

const log = debug("uk:home:login:LoginForm");

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
        log("Rendering Login Form: ", this.state.providers);
        const buttons = this.state.providers.map((provider) =>
            <SocialLoginButton type={provider} />);

      return <div>{buttons}</div>;
    }
});
