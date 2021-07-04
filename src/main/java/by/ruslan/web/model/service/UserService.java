package by.ruslan.web.model.service;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserService} interface represents sport user service
 *
 * @author Ruslan Nedvedskiy
 */
public interface UserService {

    /**
     * Find all users
     *
     * @return List of users
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find user by passed email.
     *
     * @param email user email
     * @return Optional of User entity
     * @throws ServiceException the service exception
     */
    Optional<User> findByEmail(String email) throws ServiceException;

    /**
     * Find user by passed id.
     *
     * @param userId user id
     * @return Optional of User entity
     * @throws ServiceException the service exception
     */
    Optional<User> findByUserId(long userId) throws ServiceException;

    /**
     * Register user
     *
     * @param user User entity
     * @param password password
     * @return boolean Operation success
     * @throws ServiceException the service exception
     */
    boolean registerUser(User user, String password) throws ServiceException;

    /**
     * Authorize user
     *
     * @param email user email
     * @param password password
     * @return Optional of User entity
     * @throws ServiceException the service exception
     */
    Optional<User> authorizeUser(String email, String password) throws ServiceException;

    /**
     * Update user
     *
     * @param user User entity
     * @return boolean Operation success
     * @throws ServiceException the service exception
     */
    boolean updateUser(User user) throws ServiceException;
}
