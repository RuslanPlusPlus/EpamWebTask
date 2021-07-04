package by.ruslan.web.model.service.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.exception.MoneyAccountException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.dao.MoneyAccountDao;
import by.ruslan.web.model.dao.UserDao;
import by.ruslan.web.model.dao.impl.MoneyAccountDaoImpl;
import by.ruslan.web.model.dao.impl.UserDaoImpl;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.MoneyAccountService;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.validator.CardValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The {@code MoneyAccountServiceImpl} class represents money account service implementation
 *
 * @author Ruslan Nedvedskiy
 * @see MoneyAccountService
 */
public class MoneyAccountServiceImpl implements MoneyAccountService {

    static final Logger logger = LogManager.getLogger();
    private final MoneyAccountDao moneyAccountDao = new MoneyAccountDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<MoneyAccount> findByCardNumber(String cardNumber) throws ServiceException {
        Optional<MoneyAccount> accountOptional = Optional.empty();
        if (CardValidator.isCardNumberCorrect(cardNumber)){
            try {
                logger.debug("cardNumber is valid");
                accountOptional = moneyAccountDao.findByCardNumber(cardNumber);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return accountOptional;
    }

    @Override
    public boolean updateBalance(MoneyAccount moneyAccount, User user, BigDecimal moneyToTopUp) throws ServiceException {
        BigDecimal userUpdateBalance = user.getBalance().add(moneyToTopUp);
        BigDecimal accountUpdateBalance = moneyAccount.getBalance().subtract(moneyToTopUp);
        user.setBalance(userUpdateBalance);
        moneyAccount.setBalance(accountUpdateBalance);
        boolean result;
        try {
            // TODO: 23.06.2021 dao tranzaction 
            result = userDao.update(user) & moneyAccountDao.updateBalance(moneyAccount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}
