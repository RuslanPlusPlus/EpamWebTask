package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code ToUsersPageCommand} class is responsible for redirecting
 * to user list of admin page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToUsersPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public ToUsersPageCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        List<User> users;
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            users = userService.findAll();
            if (request.getParameter(RequestParameter.SUCCESS) != null){
                request.setAttribute(RequestAttribute.SUCCESS, request.getParameter(RequestParameter.SUCCESS));
            }
            if (request.getParameter(RequestParameter.ERROR) != null){
                request.setAttribute(RequestAttribute.ERROR, request.getParameter(RequestParameter.ERROR));
            }
            request.setAttribute(RequestAttribute.USERS, users);
            session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_USERS_PAGE);
            router.setPath(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
        }
        return router;
    }
}
