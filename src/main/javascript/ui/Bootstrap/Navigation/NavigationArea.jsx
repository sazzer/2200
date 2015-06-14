import React from 'react';

export const NavigationArea = React.createClass({
    propTypes: {
        right: React.PropTypes.bool
    },

    render: function() {
        const alignment = this.props.right ? "navbar-right" : "";
        const navClasses = ["nav", "navbar-nav", alignment].join(" ");
        return <ul className={navClasses}>
            {this.props.children}
        </ul>;
    }
});
