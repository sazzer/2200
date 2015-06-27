package uk.co.grahamcox.dirt.webapp;

import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor to set the X-Request-ID header as the Logging MDC
 */
public class RequestIdInterceptor extends HandlerInterceptorAdapter {
    /** The MDC Attribute to set the Request ID into */
    private static final String REQUEST_ID_MDC = "RequestId";

    /** The header to get the Request ID from */
    private static final String REQUEST_ID_HEADER = "X-Request-ID";

    /**
     * Before the request is processed, set the MDC with the Request ID
     * @param request the Request
     * @param response the response. Ignored
     * @param handler the handler. Ignored
     * @return true
     */
    @Override
    public boolean preHandle(final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler) {

        MDC.put(REQUEST_ID_MDC, request.getHeader(REQUEST_ID_HEADER));
        return true;
    }

    /**
     * After the request is processed, remove the MDC with the Request ID
     * @param request the Request. Ignored
     * @param response the Response. Ignored
     * @param handler the Handler. Ignored
     * @param modelAndView the Model and View. Ignored
     */
    @Override
    public void postHandle(final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler,
        final ModelAndView modelAndView) {

        MDC.remove(REQUEST_ID_MDC);
    }
}
