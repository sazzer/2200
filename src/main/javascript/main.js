import "bootstrap";
import React from "react";

import {UI} from "ui/UI";
import {getStrings} from "i18n/strings";

import "login/AuthenticatedStore";

getStrings().then(strings => {
    React.render(React.createElement(UI, {
        messages: strings
    }), document.body);
}).catch(error => {
    console.error("Error loading application", error);
    throw error;
});
