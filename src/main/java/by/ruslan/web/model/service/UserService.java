package by.ruslan.web.model.service;

import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;
}
