package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.entity.UserRole;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ChangeRoleCommand} class is responsible for changing user role.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ChangeRoleCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final UserService userService;
    private static final String ERROR_MESSAGE = "Failed to update user!";
    private static final String SUCCESS_MESSAGE = "User is successfully updated!";

    public ChangeRoleCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        String email = request.getParameter(RequestParameter.EMAIL);
        String roleStr = request.getParameter(RequestParameter.ROLE);
        logger.debug("New role:" + roleStr);
        UserRole role = UserRole.valueOf(roleStr);
        try {
            Optional<User> optionalUser = userService.findByEmail(email);
            User user = optionalUser.get();
            user.setRole(role);
            boolean userUpdate = userService.updateUser(user);
            if (!userUpdate){
                request.setAttribute(RequestAttribute.ERROR_UPDATE_USER, ERROR_MESSAGE);
                param += "&error=" + ERROR_MESSAGE;
            }
            router.setType(Router.Type.REDIRECT);
            param += "&success=" + SUCCESS_MESSAGE;
            router.setPath(PagePath.TO_USERS_PAGE + param);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router = new Router(PagePath.ERROR_500, Router.Type.FORWARD);
        }

        return router;
    }
}
