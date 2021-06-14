package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindByEmailCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public FindByEmailCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        List<User> users = new ArrayList<>();
        Router router;
        String email = request.getParameter(RequestParameter.EMAIL);
        try {
            Optional<User> user = userService.findByEmail(email);
            if (user.isPresent()){
                users.add(user.get());
                request.setAttribute(RequestAttribute.USERS, users);
            }else {
                request.setAttribute(RequestAttribute.ERROR_NO_USERS, "No user with such email found");
            }
            router = new Router(PagePath.USER_LIST, Router.Type.FORWARD);

        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router = new Router(PagePath.ERROR_500, Router.Type.FORWARD);
        }
        return router;
    }
}
