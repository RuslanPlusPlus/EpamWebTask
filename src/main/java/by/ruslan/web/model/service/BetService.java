package by.ruslan.web.model.service;

import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;

public interface BetService {
    boolean makeRate(Bet bet, User user) throws ServiceException, BetException;

}
