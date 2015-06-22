import React from "react";
import ReactIntl from "react-intl";

/**
 * The login form for when the page first loads
 */
export const LoginForm = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    /**
     * Actually render the login form
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
      return <ul></ul>;
    }
});
