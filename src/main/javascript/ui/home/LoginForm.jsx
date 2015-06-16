import React from "react";
import ReactIntl from "react-intl";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    mixins: [ReactIntl.IntlMixin],
    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <div>
            <div className="form-group">
                <label for="username">{this.getIntlMessage("page.LoginForm.username.label")}</label>
                <input type="email" className="form-control" name="username" placeholder={this.getIntlMessage("page.LoginForm.username.placeholder")}/>
            </div>
            <div className="form-group">
                <label for="password">{this.getIntlMessage("page.LoginForm.password.label")}</label>
                <input type="password" className="form-control" name="password" placeholder={this.getIntlMessage("page.LoginForm.password.placeholder")}/>
            </div>
            <button type="submit" className="btn btn-lg btn-primary btn-block">{this.getIntlMessage("page.LoginForm.buttons.login")}</button>
          </div>;
    }
});
