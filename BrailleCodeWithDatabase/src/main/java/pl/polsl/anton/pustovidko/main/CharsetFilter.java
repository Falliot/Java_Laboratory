package pl.polsl.anton.pustovidko.main;

import javax.servlet.*;
import java.io.IOException;


/**
 * CharsetFilter class for using UTF-8
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class CharsetFilter implements Filter {

    /**
     * Encoding string
     */
    private String encoding;

    /**
     * Initializer for servlet context 
     * @param config servlet configuration
     * @throws ServletException in case of invalid data
     */
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    /**
     * Filtering method
     * @param request servlet request
     * @param response  servlet response
     * @param next chain element
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        // Set the default response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    /**
     * Destroy method
     */
    public void destroy() {
    }
}