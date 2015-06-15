import React from "react";

/**
 * Bootstrap Navigation Area within a Navigation Bar
 */
export const NavigationArea = React.createClass({
    propTypes: {
        /** True for the area to be right of the screen. False for it to be left */
        right: React.PropTypes.bool
    },

    /**
     * Render the navigation area
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const alignment = this.props.right ? "navbar-right" : "";
        const navClasses = ["nav", "navbar-nav", alignment].join(" ");
        return <ul className={navClasses}>
            {this.props.children}
        </ul>;
    }
});
