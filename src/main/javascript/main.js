import "bootstrap";
import React from "react";
import debug from "visionmedia-debug";
import {UI} from "ui/UI";
import {getStrings} from "i18n/strings";

import "login/AuthenticatedStore";

window.debugLogger = debug;

const log = debug("main");

getStrings().then(strings => {
    log("Rendering UI");
    React.render(React.createElement(UI, {
        messages: strings
    }), document.body);
    log("Rendered UI");
}).catch(error => {
    console.error("Error loading application", error);
    throw error;
});
