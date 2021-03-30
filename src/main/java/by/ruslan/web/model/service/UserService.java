package by.ruslan.web.model.service;

import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws ServiceException;
    Optional<User> findByEmail(String email) throws ServiceException;
}
