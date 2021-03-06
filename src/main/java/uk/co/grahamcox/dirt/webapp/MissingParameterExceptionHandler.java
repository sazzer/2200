package uk.co.grahamcox.dirt.webapp;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handler for handling Missing Parameters
 */
@ControllerAdvice
public class MissingParameterExceptionHandler {
    /**
     * Handle when a requst parameter is missing from the request
     * @param e the exception to handle
     * @return the response
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleMissingParameters(final MissingServletRequestParameterException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "MISSING_PARAMETER");
        response.put("parameter", e.getParameterName());
        return response;
    }
}
