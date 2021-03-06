import React from "react";
import ReactIntl from "react-intl";

/**
 * Bootstrap Alert area
 */
export const SocialLoginButton = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        /** The type of social login button to show */
        type: React.PropTypes.string.required,
        /** Handler for when the button is clicked */
        onClick: React.PropTypes.function
    },

    /**
     * Render the navigation link
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const outerClasses = [
            "btn",
            "btn-block",
            "btn-social",
            "btn-" + this.props.type
        ];
        const innerClasses = [
            "fa",
            "fa-" + this.props.type
        ];

        const url = "/api/authentication/external/request/" + this.props.type;
        
        return <a className={outerClasses.join(" ")} href={url} target="_blank">
            <i className={innerClasses.join(" ")}></i>
            {this.getIntlMessage("page.LoginForm.social." + this.props.type)}
        </a>;
    }
});
