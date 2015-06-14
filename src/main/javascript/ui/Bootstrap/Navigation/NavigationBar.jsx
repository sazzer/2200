import React from 'react';
import ReactIntl from 'react-intl';

/**
 * Bootstrap Navigation Bar
 */
export const NavigationBar = React.createClass({
    mixins: [ReactIntl.IntlMixin],

    propTypes: {
        /** The message key for the label to display as the navigation bar branding */
        brand: React.PropTypes.string.required
    },

    /**
     * Render the Navigation Bar
     */
    render: function() {
        let leftBars = this.props.children
            .filter(child => !child.props.right);
        let rightBars = this.props.children
            .filter(child => child.props.right)
            .reverse();

        return <nav className="navbar navbar-default navbar-static-top">
            <div className="container">
                <div className="navbar-header">
                    <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span className="sr-only">{this.getIntlMessage('Bootstrap.NavigationBar.toggle')}</span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                    </button>
                    <a className="navbar-brand" href="#">{this.getIntlMessage(this.props.brand)}</a>
                </div>
                <div id="navbar" className="navbar-collapse collapse">
                    {leftBars}
                    {rightBars}
                </div>
            </div>
        </nav>;
    }
});
