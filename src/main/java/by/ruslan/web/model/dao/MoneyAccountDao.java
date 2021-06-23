package by.ruslan.web.model.dao;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.service.MoneyAccountService;

import java.util.Optional;

public interface MoneyAccountDao {
    Optional<MoneyAccount> findByCardNumber(String cardNumber) throws DAOException;
    boolean updateBalance(MoneyAccount moneyAccount) throws DAOException;
}
