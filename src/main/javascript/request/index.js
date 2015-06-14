import superagent from 'superagent';

/**
 * Perform an Ajax request
 * @param url {String} The URL to request. Required
 * @param options {Object} The options to the request
 * @param options.method {String} The HTTP Method to use. Defaults to GET
 * @param options.accept {String} The datatype to expect. Defaults to application/json
 * @param options.parser {Function} Mechanism to parse the response type. Defaults to JSON.parse
 * @return {Promise} A promise for the result
 */
export function request(url, {
    method = 'GET',
    accept = 'application/json',
    parser = JSON.parse,
    data,
    dataType = 'application/json'
    } = {}) {

    return new Promise((resolve, reject) => {
        const request = superagent(method, url)
            .set('Accept', accept);

        if (data) {
            request.set('Content-Type', dataType)
                .send(data);
        }

        request.end((err, res) => {
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
                resolve(response);
            } else {
                reject(response);
            }
        });
    });
}
