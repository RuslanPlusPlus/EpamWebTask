package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.service.MoneyAccountService;

import java.util.Optional;

/**
 * The {@code MoneyAccountDao} interface represents money account dao
 *
 * @author Ruslan Nedvedskiy
 */

public interface MoneyAccountDao {

    /**
     * Find money account by passed card number.
     *
     * @param cardNumber card number
     * @return Optional of MoneyAccount entity
     * @throws DAOException the dao exception
     */
    Optional<MoneyAccount> findByCardNumber(String cardNumber) throws DAOException;

    /**
     * Update money account balance in database table.
     *
     * @param moneyAccount MoneyAccount entity
     * @return boolean Operation success
     * @throws DAOException the dao exception
     */
    boolean updateBalance(MoneyAccount moneyAccount) throws DAOException;
}
