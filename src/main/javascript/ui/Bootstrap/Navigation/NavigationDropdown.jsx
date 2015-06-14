import React from 'react';
import ReactIntl from 'react-intl';

export const NavigationDropdown = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        label: React.PropTypes.string.required
    },

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
