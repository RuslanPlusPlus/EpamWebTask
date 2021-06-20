package by.ruslan.web.model.dao.impl;

import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.UsersColumn;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.UserRole;
import by.ruslan.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_USERS_BY_EMAIL =
            "SELECT user_id, username, email, password, balance, role FROM users WHERE email=?";
    private static final String SQL_ADD_USER =
            "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT user_id, email, username, balance, role FROM users WHERE email = ? AND password = ?";
    private static final String UPDATE_USER =
            "UPDATE users SET username = ?, email = ?, password = ?, balance = ?, role = ? WHERE user_id = ?";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while(resultSet.next()){
                User user = buildUser(resultSet);
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
                User user = buildUser(resultSet);
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findUserById(long userId) throws DAOException {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = buildUser(resultSet);
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
            String username = user.getUsername();
            String email = user.getEmail();
            String role = user.getRole().getValue();
            logger.debug("Email: " + email);
            logger.debug("username: " + username);
            logger.debug("password " + encryptedPassword);
            logger.debug("role " + role);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, encryptedPassword);
            statement.setString(4, role);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    @Override
    public boolean update(User user) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER)){
            long userId = user.getUserId();
            String username = user.getUsername();
            String email = user.getEmail();
            String password = user.getEncodedPassword();
            BigDecimal balance = user.getBalance();
            String role = user.getRole().getValue();
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setBigDecimal(4, balance);
            statement.setString(5, role);
            statement.setLong(6, userId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt(UsersColumn.USER_ID);
        String email = resultSet.getString(UsersColumn.EMAIL);
        String userName = resultSet.getString(UsersColumn.USERNAME);
        String password = resultSet.getString(UsersColumn.PASSWORD);
        UserRole role = UserRole.valueOf(resultSet.getString(UsersColumn.ROLE));
        BigDecimal balance = resultSet.getBigDecimal(UsersColumn.BALANCE);
        User user = new User(userId, email, userName, role);
        user.setBalance(balance);
        user.setEncodedPassword(password);
        return user;
    }

}
