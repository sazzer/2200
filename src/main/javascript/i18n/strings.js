import {request} from "request";

/**
 * Get the Strings that we want to use for the application
 * @return {Promise} A Promise for the strings to use
 */
export function getStrings() {
    return request("/resources/messages/messages")
        .then(response => response.data);
}
