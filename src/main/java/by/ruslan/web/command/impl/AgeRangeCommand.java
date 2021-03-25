package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.entity.User;
import by.ruslan.web.service.UserService;
import by.ruslan.web.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AgeRangeCommand implements Command {

    private static final String LOWER_RANGE_VALUE = "lowerValue";
    private static final String HIGHER_RANGE_VALUE = "higherValue";
    private static final String ATTRIBUTE_NAME = "list";
    private static final String ATTRIBUTE_ERROR = "errorMessage";
    private final UserService userService;

    public AgeRangeCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String lowerStringValue = request.getParameter(LOWER_RANGE_VALUE);
        String higherStringValue = request.getParameter(HIGHER_RANGE_VALUE);
        boolean lowerValid = DataValidator.isValidInteger(lowerStringValue);
        boolean higherValid = DataValidator.isValidInteger(higherStringValue);
        if (!lowerValid || !higherValid){
            request.setAttribute(ATTRIBUTE_ERROR, "Invalid values entered! Please, try again");
            return PagePath.ERROR;
        }

        int lowerValue = Integer.parseInt(lowerStringValue);
        int higherValue = Integer.parseInt(higherStringValue);
        List<User> users = userService.findUsersInAgeRange(lowerValue, higherValue);
        request.setAttribute(ATTRIBUTE_NAME, users);
        return PagePath.RANGE_LIST;
    }
}
