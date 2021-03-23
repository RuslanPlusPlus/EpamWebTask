package by.ruslan.web.service;

import by.ruslan.web.entity.User;
import by.ruslan.web.repository.Specification;

import java.util.Comparator;
import java.util.List;

public interface UserService {
    List<User> sortUsers(Comparator<User> comparator);
    List<User> query(Specification<User> specification);
    List<User> getAll();
    void addAll(List<User> users);
}
