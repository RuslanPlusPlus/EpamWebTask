package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final UserService userService;

    public SignUpCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String username = request.getParameter(RequestParameter.USERNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        Router router;
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        try {
            Optional<User> similarUser = userService.findByEmail(email);
            if (similarUser.isPresent()){
                request.setAttribute(RequestAttribute.EMAIL_EXISTS, "User with such email already exists");
                router = new Router(PagePath.SIGN_UP, Router.Type.FORWARD);
            }else {
                boolean isUserRegistered = userService.addUser(user, password);
                if (isUserRegistered){
                    logger.info("User email " + email + " successfully registered");
                    router = new Router(PagePath.INDEX_JSP, Router.Type.REDIRECT);
                }else {
                    request.setAttribute(RequestAttribute.INVALID_DATA, "Invalid data entered! Try again");
                    router = new Router(PagePath.SIGN_UP, Router.Type.FORWARD);
                }
            }

        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router = new Router(PagePath.ERROR_500, Router.Type.FORWARD);
        }
        return router;
    }
}
