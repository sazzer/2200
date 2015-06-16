import React from "react";
import {HeaderBar} from "ui/HeaderBar";
import {HomeScreen} from "ui/home/HomeScreen";

/**
 * The main UI for the entire application
 */
export const UI = React.createClass({
    /**
     * Specify the properties that are used by this class
     */
    propTypes: {
        /** The messages that are used for translations */
        messages: React.PropTypes.object.isRequired
    },

    /**
     * Specify the types of the context provided to child objects
     */
    childContextTypes: {
        /** The messages that are used for translations */
        messages: React.PropTypes.object.isRequired
    },

    /**
     * Get the context of all child elements
     * @return {object} the context. This is the i18n strings to use
     */
    getChildContext: function() {
        return {
            messages: this.props.messages
        };
    },

    /**
     * Actually render the main user interface
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <div>
            <HeaderBar />
            <HomeScreen />
        </div>;
    }
});
