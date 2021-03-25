package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.entity.User;
import by.ruslan.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayCommand implements Command {

    private final UserService userService;

    public DisplayCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.getAll();
        request.setAttribute("list", users);
        return PagePath.LIST;
    }
}
