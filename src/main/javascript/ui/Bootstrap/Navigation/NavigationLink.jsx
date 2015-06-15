import React from "react";
import ReactIntl from "react-intl";

/**
 * Bootstrap Navigation Link in a Navigation Area or Dropdown
 */
export const NavigationLink = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        /** The label of the navigation link */
        label: React.PropTypes.string.required
    },

    /**
     * Render the navigation link
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <li><a href="#">{this.getIntlMessage(this.props.label)}</a></li>;
    }
});
