import React from 'react';
import {NavigationBar, NavigationArea, NavigationLink, NavigationDropdown} from 'ui/Bootstrap/Navigation';

/**
 * The header bar that is displayed at the top of the screen
 */
export const HeaderBar = React.createClass({
    /**
     * Actually render the header bar
     */
    render: function() {
        return <NavigationBar brand="page.title">
            <NavigationArea>
                <NavigationLink label="Home" />
                <NavigationLink label="About" />
                <NavigationLink label="Contact" />
            </NavigationArea>
            <NavigationArea right>
                <NavigationDropdown label="Dropdown">
                    <NavigationLink label="Action" />
                    <NavigationLink label="Anothe rAction" />
                    <NavigationLink label="Something Else Here" />
                </NavigationDropdown>
            </NavigationArea>
        </NavigationBar>;
    }
});
