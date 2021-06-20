package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User>{
    Optional<User> findUserByEmail(String name) throws DAOException;
    Optional<User> findUserById(long userId) throws DAOException;
    boolean add(User user, String encryptedPassword) throws DAOException;
    boolean update(User user) throws DAOException;
}
