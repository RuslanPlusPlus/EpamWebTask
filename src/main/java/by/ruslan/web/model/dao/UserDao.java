package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.User;

import java.util.Optional;

/**
 * The {@code UserDao} interface represents user dao
 *
 * @author Ruslan Nedvedskiy
 */
public interface UserDao extends BaseDao<User>{

    /**
     * Find user by passed email.
     *
     * @param email user email
     * @return Optional of User entity
     * @throws DAOException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DAOException;

    /**
     * Find user by passed id.
     *
     * @param userId user id
     * @return Optional of User entity
     * @throws DAOException the dao exception
     */
    Optional<User> findUserById(long userId) throws DAOException;

    /**
     * Add Event entity to database table.
     *
     * @param user User entity
     * @param encryptedPassword Encrypted password
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean add(User user, String encryptedPassword) throws DAOException;

    /**
     * Update User entity in database table.
     *
     * @param user User entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean update(User user) throws DAOException;
}
