import 'bootstrap';
import React from 'react';

import {UI} from 'ui/UI';
import {getStrings} from 'i18n/strings';

getStrings().then(strings => {
    React.render(React.createElement(UI, {
        messages: strings
    }), document.body);
}).catch(error => {
    alert("Failed to load the application");
    console.error("Failed to load the application", error);
    throw error;
});
