package by.ruslan.web.model.service;

import by.ruslan.web.exception.MoneyAccountException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface MoneyAccountService {
    Optional<MoneyAccount> findByCardNumber(String cardNumber) throws ServiceException;
    boolean updateBalance(MoneyAccount moneyAccount, User user, BigDecimal moneyToTopUp) throws ServiceException;
}
