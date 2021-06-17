/*package by.ruslan.web.controller.filter;

import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.SessionAttribute;
import by.ruslan.web.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/main-servlet" }, servletNames = { "MainServlet" })
public class ControllerSecurityFilter implements Filter {

    static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(SessionAttribute.ROLE);
        if (role == UserRole.GUEST){
            logger.debug("Access is forbidden for guest! User is forwarded to login page");
            request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN).forward(request, response);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}*/
