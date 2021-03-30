package by.ruslan.web.model.dao.impl;

import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.UsersColumn;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.pool.ConnectionCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT user_id, username, email, FROM users";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while(resultSet.next()){
                long userId = resultSet.getInt(UsersColumn.USER_ID);
                String userName = resultSet.getString(UsersColumn.USERNAME);
                String email = resultSet.getString(UsersColumn.EMAIL);
                User user = new User(userId, email, userName);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            try {
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Failed to close connection");
            }
        }
        return users;
    }

    @Override
    public List<User> findUsersByName(String name) {
        // TODO: 26.03.2021 realization
        return new ArrayList<>();
    }

}
