package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.BetDao;
import by.ruslan.web.model.dao.EventDao;
import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.BetDaoImpl;
import by.ruslan.web.model.dao.impl.EventDaoImpl;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.BetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class BetServiceImpl implements BetService {

    static final Logger logger = LogManager.getLogger();
    private final BetDao betDao = new BetDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private static final String SMALL_BALANCE = "User has not enough money to make such bet";
    private static final String THE_SAME_BET = "User has already make bet on this event";

    @Override
    public boolean makeRate(Bet bet, User user) throws ServiceException, BetException {
        boolean result;
        BetException betException = new BetException();
        try {
            BigDecimal userBalance = user.getBalance();
            BigDecimal betMoney = bet.getMoney();
            if (userBalance.compareTo(betMoney) < 0){
                betException.setErrorMessage(SMALL_BALANCE);
                throw betException;
            }
            List<Bet> activeBets = user.getActiveBets();
            for (Bet activeBet: activeBets){
                if (activeBet.getEventId() == bet.getEventId()){
                    logger.debug("SAME EVENT ID");
                    betException.setErrorMessage(THE_SAME_BET);
                    throw betException;
                }
            }
            BigDecimal restMoney = userBalance.subtract(betMoney);
            user.setBalance(restMoney);
            result = userDao.update(user) & betDao.add(bet);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }


}
