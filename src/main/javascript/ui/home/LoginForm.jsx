import React from "react";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <form>
            <div className="form-group">
                <label for="username">Email Address</label>
                <input type="email" className="form-control" name="username" placeholder="Enter Email Address"/>
            </div>
            <div className="form-group">
                <label for="password">Password</label>
                <input type="password" className="form-control" name="password" placeholder="Enter Password"/>
            </div>
            <div className="">
                <button type="submit" className="btn btn-primary">Log In</button>
                <button type="submit" className="btn">Register</button>
            </div>
        </form>;
    }
});
