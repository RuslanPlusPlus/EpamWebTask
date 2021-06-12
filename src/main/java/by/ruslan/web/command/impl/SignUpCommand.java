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
        Router router = new Router();
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        try {
            Optional<User> similarUser = userService.findByEmail(email);
            if (similarUser.isPresent()){
                request.setAttribute(RequestAttribute.EMAIL_EXISTS, "User with such email already exists");
                router.setPath(PagePath.SIGN_UP);
            }else {
                boolean isUserRegistered = userService.registerUser(user, password);
                if (isUserRegistered){
                    logger.info("User email " + email + " successfully registered");
                    router.setPath(PagePath.INDEX_JSP);
                    router.setType(Router.Type.REDIRECT);
                }else {
                    request.setAttribute(RequestAttribute.INVALID_DATA, "Invalid data entered! Try again");
                    router.setPath(PagePath.SIGN_UP);
                }
            }

        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
        }
        return router;
    }
}
