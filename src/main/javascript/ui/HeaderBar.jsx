import React from "react";
import {NavigationBar, NavigationArea, NavigationLink, NavigationDropdown} from "ui/Bootstrap/Navigation";

/**
 * The header bar that is displayed at the top of the screen
 */
export const HeaderBar = React.createClass({
    /**
     * Actually render the header bar
     * @return {ReactElement} The React representation of this element
     */
    render: function() {
        return <NavigationBar brand="page.title">
            <NavigationArea right>
                <NavigationDropdown label="page.menu.help.label">
                </NavigationDropdown>
            </NavigationArea>
            <NavigationArea right>
                <NavigationLink label="page.menu.account.profile" />
                <NavigationLink label="page.menu.account.characters" />
                <NavigationLink label="page.menu.account.logout" />
            </NavigationArea>
        </NavigationBar>;
    }
});
