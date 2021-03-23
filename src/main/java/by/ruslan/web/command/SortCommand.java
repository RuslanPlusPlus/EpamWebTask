package by.ruslan.web.command;

import by.ruslan.web.comparator.UserAgeComparator;
import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SortCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        UserAgeComparator comparator = new UserAgeComparator();
        UserRepository repository = UserRepository.getInstance();
        List<User> users = repository.sort(comparator);
        request.setAttribute("list", users);
        return "/pages/sortedList.jsp";
    }
}
