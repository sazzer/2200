import React from "react";

/**
 * The splash screen giving information about the game
 */
export const SplashScreen = React.createClass({
    /**
     * Actually render the splash screen
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <div className="jumbotron">
            Splash Screen Here
        </div>;
    }
});
