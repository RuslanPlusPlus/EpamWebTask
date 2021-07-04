package by.ruslan.web.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code PageAccessFilter} class is responsible for
 * preventing direct access to jsp pages.
 *
 * @author Ruslan Nedvedskiy
 */

@WebFilter(urlPatterns = {"/pages/*"},
            initParams = {@WebInitParam(name = "HOME_PATH", value = "/index.jsp")
})
public class PageAccessFilter implements Filter {

    private String path;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        path = filterConfig.getInitParameter("HOME_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + path);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        path = null;
    }
}
