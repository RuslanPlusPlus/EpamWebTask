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

public class ToUsersPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private UserService userService;

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
            logger.debug(users);
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
