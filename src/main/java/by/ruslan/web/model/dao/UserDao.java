package by.ruslan.web.model.dao;

import by.ruslan.web.model.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<Long, User>{
    List<User> findUsersByName(String name);
}
