package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.RequestAttribute;
import by.ruslan.web.command.Router;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllUsersCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public FindAllUsersCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        List<User> users;
        Router router;
        try {
            users = userService.findAll();
            request.setAttribute(RequestAttribute.USERS, users);
            router = new Router(PagePath.USER_LIST, Router.Type.FORWARD);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router = new Router(PagePath.ERROR_500, Router.Type.FORWARD);
        }
        return router;
    }
}
