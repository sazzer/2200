import React from "react";
import Reflux from "reflux";
import {NavigationBar, NavigationArea, NavigationLink, NavigationDropdown} from "ui/Bootstrap/Navigation";
import {AuthenticatedStore} from "login/AuthenticatedStore";

/**
 * The header bar that is displayed at the top of the screen
 */
export const HeaderBar = React.createClass({
    mixins: [Reflux.connect(AuthenticatedStore)],

    /**
     * Actually render the header bar
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        let navAreas = [];
        navAreas.push(<NavigationArea right>
            <NavigationDropdown label="page.menu.help.label">
            </NavigationDropdown>
        </NavigationArea>);

        if (this.state.authenticated) {
            navAreas.push(<NavigationArea right>
                <NavigationLink label="page.menu.account.profile" />
                <NavigationLink label="page.menu.account.characters" />
                <NavigationLink label="page.menu.account.logout" />
            </NavigationArea>);
        }

        return <NavigationBar brand="page.title">
            {navAreas}
        </NavigationBar>;
    }
});
