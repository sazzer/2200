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
      return <div>
          <a className="btn btn-block btn-social btn-facebook">
              <i className="fa fa-facebook"></i> Sign in with Facebook
          </a>
          <a className="btn btn-block btn-social btn-google">
              <i className="fa fa-google"></i> Sign in with Google
          </a>
          <a className="btn btn-block btn-social btn-twitter">
              <i className="fa fa-twitter"></i> Sign in with Twitter
          </a>
      </div>;
    }
});
