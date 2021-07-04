package by.ruslan.web.model.entity;

import java.math.BigDecimal;

/**
 * The {@code MoneyAccount} class describes the MoneyAccount entity
 *
 * @author Ruslan Nedvedskiy
 */
public class MoneyAccount {

    private long accountId;
    private String cardNumber;
    private BigDecimal balance;

    public MoneyAccount() {
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MoneyAccount{" +
                "accountId=" + accountId +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
