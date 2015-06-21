import React from "react";
import ReactIntl from "react-intl";

/**
 * Bootstrap Input control
 */
export const Input = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        /** The name of the input control */
        name: React.PropTypes.string.required,
        /** The message key for the label */
        label: React.PropTypes.string.required,
        /** The message key for the placeholder text */
        placeholder: React.PropTypes.string.required,
        /** The value of the input box */
        value: React.PropTypes.string,
        /** Function to emit when the value changes */
        onChange: React.PropTypes.function,
        /** The type of input control */
        type: React.PropTypes.string,
        /** Whether the control is in error or not */
        error: React.PropTypes.boolean
    },

    /**
     * Get the default properties for the component.
     * This sets the mode to "danger" if not specified"
     * @return {Object} the default properties
     */
    getDefaultProps: function() {
        return {
            type: "text",
            error: false
        };
    },

    /**
     * Render the navigation link
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        const feedbackId = this.props.name + "Status";
        const inputClasses = ["form-control", "test-" + this.props.name];

        let inputGroupClasses = ["form-group"];
        let inputFeedback;
        if (this.props.error) {
            inputGroupClasses.push("has-error");
            inputGroupClasses.push("has-feedback");
            inputFeedback = [
                <span className="glyphicon glyphicon-remove form-control-feedback"
                    aria-hidden="true"></span>,
                <span id={feedbackId}
                    className="sr-only">
                    (error)
                </span>
            ];
        }

        return <div className={inputGroupClasses.join(" ")}>
            <label className="control-label"
                for={this.props.name}>
                {this.getIntlMessage(this.props.label)}
            </label>
            <input type={this.props.type}
                className={inputClasses.join(" ")}
                name={this.props.name}
                placeholder={this.getIntlMessage(this.props.placeholder)}
                aria-describedby={feedbackId}
                value={this.props.value}
                onChange={this.props.onChange} />
            {inputFeedback}
        </div>;
    }
});
