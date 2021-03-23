package by.ruslan.web.command;

import by.ruslan.web.creator.UserCreator;
import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.UserRepository;
import by.ruslan.web.service.UserService;
import by.ruslan.web.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        UserCreator creator = new UserCreator();
        List<User> users = creator.createUsers();
        UserService userService = new UserServiceImpl();
        userService.addAll(users);
        return PagePath.MAIN;
    }
}
