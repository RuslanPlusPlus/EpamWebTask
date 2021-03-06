package by.ruslan.web.controller.filter;

import by.ruslan.web.command.*;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * The {@code ControllerSecurityFilter} class is responsible filtering
 * user access to resources according user role.
 *
 * @author Ruslan Nedvedskiy
 */

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

        String commandName = request.getParameter(RequestParameter.COMMAND);
        if (commandName == null){
            logger.error("Cannot determine command name");
            request.setAttribute(RequestAttribute.ERROR, "Cannot determine command name");
            response.sendError(500);
            return;
        }
        CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        Set<CommandType> allowedCommands;

        UserRole role = UserRole.GUEST;
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user != null) {
            role = user.getRole();
        }

        allowedCommands = switch (role) {
            case CLIENT -> RoleAccessType.CLIENT.getAllowedCommands();
            case ADMIN -> RoleAccessType.ADMIN.getAllowedCommands();
            case BOOKMAKER -> RoleAccessType.BOOKMAKER.getAllowedCommands();
            default -> RoleAccessType.GUEST.getAllowedCommands();
        };

        boolean accessAllowed = false;
        if (allowedCommands.contains(commandType)){
            accessAllowed = true;
        }
        if (accessAllowed){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            request.setAttribute(RequestAttribute.ERROR, "User with role " + role + " has no access to this page");
            response.sendError(403);
        }
    }

    @Override
    public void destroy() {

    }
}
