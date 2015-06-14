import React from 'react';

export const NavigationLink = React.createClass({
    propTypes: {
        label: React.PropTypes.string.required
    },

    render: function() {
        const labelText = this.props.label;

        return <li><a href="#">{labelText}</a></li>;
    }
});
