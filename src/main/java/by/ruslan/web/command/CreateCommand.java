package by.ruslan.web.command;

import by.ruslan.web.creator.UserCreator;
import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        UserCreator creator = new UserCreator();
        List<User> users = creator.createUsers();
        UserRepository repository = UserRepository.getInstance();
        repository.addAll(users);
        return "index.jsp";
    }
}
