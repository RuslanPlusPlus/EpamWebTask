package by.ruslan.web.model.service.impl;

import by.ruslan.web.model.dao.BetDao;
import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.BetDaoImpl;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.UserRole;
import by.ruslan.web.model.service.UserService;
import by.ruslan.web.util.PasswordEncryptor;
import by.ruslan.web.util.XssProtector;
import by.ruslan.web.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserServiceImpl} class represents user service implementation
 *
 * @author Ruslan Nedvedskiy
 * @see UserService
 */
public class UserServiceImpl implements UserService {

    static final Logger logger = LogManager.getLogger();
    private final UserDao userDao = new UserDaoImpl();
    private final BetDao betDao = new BetDaoImpl();

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
        Optional<User> userOptional = Optional.empty();
        try {
            email = XssProtector.filterXss(email);
            if (UserValidator.isEmailValid(email)){
                userOptional = userDao.findUserByEmail(email);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findByUserId(long userId) throws ServiceException {
        Optional<User> userOptional;
        try {
            userOptional = userDao.findUserById(userId);
            if (userOptional.isPresent()){
                List<Bet> activeBets = betDao.findActiveBetsForUser(userId);
                List<Bet> completedBets = betDao.findCompletedBetsForUser(userId);
                User user = userOptional.get();
                user.setActiveBets(activeBets);
                user.setCompletedBets(completedBets);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userOptional;
    }

    @Override
    public boolean registerUser(User user, String password) throws ServiceException {
        boolean isAdded = false;
        try {
            if (UserValidator.isEmailValid(user.getEmail()) &&
                UserValidator.isUsernameValid(user.getUsername()) &&
                UserValidator.isPasswordValid(password)) {
                String encryptedPassword = PasswordEncryptor.encrypt(password);
                user.setRole(UserRole.CLIENT);
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
                    List<Bet> activeBets = betDao.findActiveBetsForUser(user.getUserId());
                    List<Bet> completedBets = betDao.findCompletedBetsForUser(user.getUserId());
                    user.setActiveBets(activeBets);
                    user.setCompletedBets(completedBets);
                    String userPassword = user.getEncodedPassword();
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

    @Override
    public boolean updateUser(User user) throws ServiceException {
        boolean result;
        try {
            result = userDao.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
