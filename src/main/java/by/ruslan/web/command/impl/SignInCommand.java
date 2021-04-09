package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignInCommand implements Command {

    private static final String ERROR_MESSAGE = "incorrect login or password";
    static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public SignInCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Router router;
        try {
            Optional<User> user = userService.authorizeUser(email, password);
            if (user.isPresent()){
                router = new Router(PagePath.MAIN_SERVLET_PATH, Router.Type.REDIRECT);
                logger.debug("User authorized");
            }else {
                request.setAttribute(RequestAttribute.LOGIN_ERROR, ERROR_MESSAGE);
                router = new Router(PagePath.SIGN_IN, Router.Type.FORWARD);
                logger.debug("User is not authorized");
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router = new Router(PagePath.ERROR_500, Router.Type.FORWARD);
        }
        return router;
    }
}
