package by.ruslan.web.command;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.impl.AgeRangeSpecification;
import by.ruslan.web.repository.impl.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AgeRangeCommand implements Command{

    static final String LOWER_RANGE_VALUE = "lowerValue";
    static final String HIGHER_RANGE_VALUE = "higherValue";

    @Override
    public String execute(HttpServletRequest request) {
        String lowerStringValue = request.getParameter(LOWER_RANGE_VALUE);
        String higherStringValue = request.getParameter(HIGHER_RANGE_VALUE);
        int lowerValue = Integer.parseInt(lowerStringValue);
        int higherValue = Integer.parseInt(higherStringValue);
        AgeRangeSpecification specification = new AgeRangeSpecification(lowerValue, higherValue);
        UserRepository repository = UserRepository.getInstance();
        List<User> users = repository.query(specification);
        request.setAttribute("list", users);
        return "/pages/rangeList.jsp";
    }
}
