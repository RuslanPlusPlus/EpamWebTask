package by.ruslan.web.model.service.impl;

import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.UserService;
import by.ruslan.web.util.PasswordEncryptor;
import by.ruslan.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    static final Logger logger = LogManager.getLogger();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try {
            return userDao.findUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addUser(User user, String password) throws ServiceException {
        boolean isAdded = false;
        try {
            if (UserValidator.isEmailValid(user.getEmail()) &&
                UserValidator.isNameValid(user.getUserName()) &&
                UserValidator.isPasswordValid(password)) {
                String encryptedPassword = PasswordEncryptor.encrypt(password);
                isAdded = userDao.add(user, encryptedPassword);
            }
        } catch (DAOException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public Optional<User> authorizeUser(String email, String password) throws ServiceException {
        Optional<User> userOptional = Optional.empty();
        try {
            if (UserValidator.isEmailValid(email) &&
                UserValidator.isPasswordValid(password)) {
                userOptional = userDao.findUserByEmail(email);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    logger.info(user.getEmail());
                    logger.info(user.getEnPassword());
                    String userPassword = user.getEnPassword();
                    String enPassword = PasswordEncryptor.encrypt(password);
                    if (!userPassword.equals(enPassword)) {
                        userOptional = Optional.empty();
                    }
                }
            }
        } catch (DAOException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        }
        return userOptional;
    }
}
