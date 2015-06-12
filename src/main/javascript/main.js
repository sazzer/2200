import {Stomp} from 'stomp-websocket';
import React from 'react';
import {Hello} from './Hello';
import 'bootstrap';

React.render(React.createElement(Hello), document.body);

const client = Stomp.client('ws://' + location.host + '/hello');

client.connect(undefined, undefined, () => {
    client.subscribe('/topic/greetings', (message) => {
        const greeting = JSON.parse(message.body).greeting;
        document.getElementById("homepage").innerText = greeting;
    });

    client.send('/app/hello', {}, JSON.stringify({name: 'Graham'}));
});
