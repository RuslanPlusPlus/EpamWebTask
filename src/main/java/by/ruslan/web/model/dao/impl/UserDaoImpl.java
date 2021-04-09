package by.ruslan.web.model.dao.impl;

import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.UsersColumn;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.pool.ConnectionFactory;
import by.ruslan.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT user_id, username, email, password FROM users";
    private static final String SQL_SELECT_USERS_BY_EMAIL =
            "SELECT user_id, username, email, password FROM users WHERE email=?";
    private static final String SQL_ADD_USER =
            "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while(resultSet.next()){
                long userId = resultSet.getInt(UsersColumn.USER_ID);
                String userName = resultSet.getString(UsersColumn.USERNAME);
                String email = resultSet.getString(UsersColumn.EMAIL);
                String password = resultSet.getString(UsersColumn.PASSWORD);
                User user = new User(userId, email, userName);
                user.setEnPassword(password);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DAOException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(UsersColumn.USER_ID);
                String userName = resultSet.getString(UsersColumn.USERNAME);
                String password = resultSet.getString(UsersColumn.PASSWORD);
                User user = new User(userId, email, userName);
                user.setEnPassword(password);
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return userOptional;
    }

    @Override
    public boolean add(User user, String encryptedPassword) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
            String username = user.getUserName();
            String email = user.getEmail();
            logger.debug("Email: " + email);
            logger.debug("username: " + username);
            logger.debug("password " + encryptedPassword);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, encryptedPassword);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

}
