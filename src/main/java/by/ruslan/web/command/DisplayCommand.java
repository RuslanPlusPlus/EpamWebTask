package by.ruslan.web.command;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.UserRepository;
import by.ruslan.web.service.UserService;
import by.ruslan.web.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAll();
        request.setAttribute("list", users);
        return PagePath.LIST;
    }
}
