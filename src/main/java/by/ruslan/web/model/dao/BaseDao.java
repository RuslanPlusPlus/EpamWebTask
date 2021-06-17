package by.ruslan.web.model.dao;

import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;

import java.util.List;

public interface BaseDao<T> {
    List<T> findAll() throws DAOException;
}
