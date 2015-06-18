import superagent from "superagent";

/**
 * Perform an Ajax request
 * @param {String} url The URL to request. Required
 * @param {Object} options The options to the request
 * @param {String} options.method The HTTP Method to use. Defaults to GET
 * @param {String} options.accept The datatype to expect. Defaults to application/json
 * @param {Function} options.parser Mechanism to parse the response type. Defaults to JSON.parse
 * @return {Promise} A promise for the result
 */
export function request(url, {
    method = "GET",
    accept = "application/json",
    parser = JSON.parse,
    data,
    dataType = "application/json"
    } = {}) {

    return new Promise((resolve, reject) => {
        const superagentRequest = superagent(method, url)
            .set("Accept", accept);

        if (data) {
          superagentRequest.set("Content-Type", dataType)
                .send(data);
        }

        superagentRequest.end((err, res) => {
            const parsed = parser ? parser(res.text) : res.text;
            const response = {
                res: res,
                text: res.text,
                headers: res.headers,
                status: res.status,
                statusText: res.statusText,
                data: parsed
            };

            if (res.ok) {
                console.log("HTTP Request OK");
                resolve(response);
            } else {
                console.log("HTTP Error");
                reject(response);
            }
        });
    });
}
