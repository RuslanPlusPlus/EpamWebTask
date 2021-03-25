package by.ruslan.web.service;

import by.ruslan.web.entity.User;
import java.util.List;

public interface UserService {
    List<User> sortUsersByAge();
    List<User> findUsersInAgeRange(int lowerValue, int higherValue);
    List<User> getAll();
}
