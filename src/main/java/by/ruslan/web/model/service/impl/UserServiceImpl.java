package by.ruslan.web.model.service.impl;

import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
