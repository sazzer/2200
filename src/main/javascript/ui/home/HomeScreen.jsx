import React from "react";
import {SplashScreen} from "ui/home/SplashScreen";
import {LoginForm} from "ui/home/LoginForm";

/**
 * The landing screen for when the page first loads
 */
export const HomeScreen = React.createClass({
    /**
     * Actually render the home screen
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <div className="container-fluid">
            <div className="col-md-8">
                <SplashScreen />
            </div>
            <div className="col-md-4">
                <LoginForm />
            </div>
        </div>;
    }
});
