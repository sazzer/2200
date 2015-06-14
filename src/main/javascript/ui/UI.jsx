import React from 'react';
import {HeaderBar} from 'ui/HeaderBar';

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
     * @return the context. This is the i18n strings to use
     */
    getChildContext: function() {
        return {
            messages: this.props.messages
        };
    },

    /**
     * Actually render the main user interface
     */
    render: function() {
        return <HeaderBar />;
    }
});
