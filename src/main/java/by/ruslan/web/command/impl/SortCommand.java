package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.entity.User;
import by.ruslan.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SortCommand implements Command {

    private final UserService userService;
    private static final String ATTRIBUTE_NAME = "list";

    public SortCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.sortUsersByAge();
        request.setAttribute(ATTRIBUTE_NAME, users);
        return PagePath.SORTED_LIST;
    }
}
