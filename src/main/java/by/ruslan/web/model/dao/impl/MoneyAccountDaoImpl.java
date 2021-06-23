package by.ruslan.web.model.dao.impl;

import by.ruslan.web.exception.DAOException;
import by.ruslan.web.model.dao.MoneyAccountColumn;
import by.ruslan.web.model.dao.MoneyAccountDao;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class MoneyAccountDaoImpl implements MoneyAccountDao {

    static final Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_BY_CARD_NUMBER = "SELECT * FROM money_accounts WHERE card_number LIKE ?";
    private static final String SQL_UPDATE = "UPDATE money_accounts SET balance = ? WHERE account_id = ?";

    @Override
    public Optional<MoneyAccount> findByCardNumber(String cardNumber) throws DAOException {
        Optional<MoneyAccount> accountOptional = Optional.empty();
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_CARD_NUMBER)){
            statement.setString(1, cardNumber);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                long accountId = resultSet.getLong(MoneyAccountColumn.ACCOUNT_ID);
                BigDecimal balance = resultSet.getBigDecimal(MoneyAccountColumn.BALANCE);
                MoneyAccount moneyAccount = new MoneyAccount();
                moneyAccount.setAccountId(accountId);
                moneyAccount.setCardNumber(cardNumber);
                moneyAccount.setBalance(balance);
                logger.debug(moneyAccount);
                accountOptional = Optional.of(moneyAccount);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return accountOptional;
    }

    @Override
    public boolean updateBalance(MoneyAccount moneyAccount) throws DAOException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
            BigDecimal balance = moneyAccount.getBalance();
            statement.setBigDecimal(1, balance);
            statement.setLong(2, moneyAccount.getAccountId());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException();
        }
        return result;
    }
}
