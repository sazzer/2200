import React from 'react';
import {NavigationBar, NavigationArea, NavigationLink, NavigationDropdown} from 'ui/Bootstrap/Navigation';

export const HeaderBar = React.createClass({
    render: function() {
        return <NavigationBar>
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
