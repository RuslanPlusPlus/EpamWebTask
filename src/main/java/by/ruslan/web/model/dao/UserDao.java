package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User>{
    Optional<User> findUserByEmail(String name) throws DAOException;
}
