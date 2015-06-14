import React from 'react';

export const NavigationDropdown = React.createClass({
    propTypes: {
        label: React.PropTypes.string.required
    },

    render: function() {
        const labelText = this.props.label;

        return <li className="dropdown">
            <a href="#" className="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">{labelText} <span className="caret"></span></a>
            <ul className="dropdown-menu" role="menu">
                {this.props.children}
            </ul>
        </li>;
    }
});
