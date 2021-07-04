package by.ruslan.web.model.dao;

import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;

import java.util.List;

/**
 * The {@code BaseDao} interface represents root interface in the dao hierarchy
 *
 * @author Ruslan Nedvedskiy
 * @param <T> entity
 */

public interface BaseDao<T> {

    /**
     * Find all records in the corresponding database table
     *
     * @return List of entities
     * @throws DAOException dao exception
     */

    List<T> findAll() throws DAOException;
}
