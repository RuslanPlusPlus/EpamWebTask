package by.ruslan.web.repository;

import by.ruslan.web.entity.User;

public interface Specification<T extends User> {
    boolean specify(T object);
}
