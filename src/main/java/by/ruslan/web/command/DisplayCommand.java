package by.ruslan.web.command;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        UserRepository repository = UserRepository.getInstance();
        List<User> users = repository.getAll();
        request.setAttribute("list", users);
        return "/pages/list.jsp";
    }
}
