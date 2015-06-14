import React from 'react';
import ReactIntl from 'react-intl';

/**
 * Bootstrap Dropdown in a Navigation Area
 */
export const NavigationDropdown = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        /** The label of the dropdown */
        label: React.PropTypes.string.required
    },

    /**
     * Render the dropdown
     */
    render: function() {
        const labelText = this.props.label;

        return <li className="dropdown">
            <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                {this.getIntlMessage(this.props.label)} <span className="caret"></span>
            </a>
            <ul className="dropdown-menu" role="menu">
                {this.props.children}
            </ul>
        </li>;
    }
});
