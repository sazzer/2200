import React from "react";

/**
 * Bootstrap Alert area
 */
export const Alert = React.createClass({

    propTypes: {
        /** The mode of the alery area */
        mode: React.PropTypes.string
    },

    /**
     * Get the default properties for the component.
     * This sets the mode to "danger" if not specified"
     * @return {Object} the default properties
     */
    getDefaultProps: function() {
        return {
            mode: "danger"
        };
    },

    /**
     * Render the navigation link
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const classes = [
            "alert",
            "alert-" + this.props.mode
        ];
        return <div className={classes.join(" ")} role="alert">
            {this.props.children}
        </div>;
    }
});
