package by.ruslan.web.model.service;

import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws ServiceException;
    Optional<User> findByEmail(String email) throws ServiceException;
    Optional<User> findByUserId(long userId) throws ServiceException;
    boolean registerUser(User user, String password) throws ServiceException;
    Optional<User> authorizeUser(String email, String password) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;
}
