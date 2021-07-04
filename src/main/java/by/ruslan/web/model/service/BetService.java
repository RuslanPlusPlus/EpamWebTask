package by.ruslan.web.model.service;

import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;

/**
 * The {@code BetService} interface represents bet service
 *
 * @author Ruslan Nedvedskiy
 */
public interface BetService {

    /**
     * Makes rate.
     *
     * @param bet Bet entity
     * @param user User entity
     * @return boolean Operation success
     * @throws ServiceException the service exception
     * @throws BetException the bet exception
     */
    boolean makeRate(Bet bet, User user) throws ServiceException, BetException;
}
