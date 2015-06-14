import React from 'react';
import ReactIntl from 'react-intl';

export const NavigationLink = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        label: React.PropTypes.string.required
    },

    render: function() {
        return <li><a href="#">{this.getIntlMessage(this.props.label)}</a></li>;
    }
});
