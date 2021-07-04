package by.ruslan.web.model.service;

import by.ruslan.web.exception.MoneyAccountException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The {@code MoneyAccountService} interface represents money account service
 *
 * @author Ruslan Nedvedskiy
 */
public interface MoneyAccountService {

    /**
     * Find MoneyAccount entity by card number
     *
     * @param cardNumber card number
     * @return Optional of MoneyAccount entity
     * @throws ServiceException the service exception
     */
    Optional<MoneyAccount> findByCardNumber(String cardNumber) throws ServiceException;

    /**
     * Update MoneyAccount entity
     *
     * @param moneyAccount money account
     * @param user user
     * @param moneyToTopUp money to top up balance
     * @return boolean success
     * @throws ServiceException the service exception
     */
    boolean updateBalance(MoneyAccount moneyAccount, User user, BigDecimal moneyToTopUp) throws ServiceException;
}
