package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.MoneyAccount;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.MoneyAccountService;
import by.ruslan.web.validator.CardValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

public class TopUpBalanceCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final MoneyAccountService moneyAccountService;
    private static final String SUCCESS_MESSAGE = "The balance is successfully topped up!!!";
    private static final String ERROR_MESSAGE_NOT_ENOUGH_MONEY = "You have not enough money on this account !!!";
    private static final String ERROR_INCORRECT_CARD_NUMBER = "Incorrect card number !!!";

    public TopUpBalanceCommand(MoneyAccountService moneyAccountService){
        this.moneyAccountService = moneyAccountService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
        String moneyAmountStr = request.getParameter(RequestParameter.AMOUNT);
        BigDecimal moneyAmount = BigDecimal.valueOf(Double.parseDouble(moneyAmountStr));
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);

        try {
            Optional<MoneyAccount> accountOptional = moneyAccountService.findByCardNumber(cardNumber);
            if (accountOptional.isPresent()){
                MoneyAccount account = accountOptional.get();
                BigDecimal accountBalance = account.getBalance();
                if (accountBalance.compareTo(moneyAmount) < 0){
                    request.setAttribute(RequestAttribute.ERROR_TOP_UP_BALANCE, ERROR_MESSAGE_NOT_ENOUGH_MONEY);
                    router.setPath(PagePath.TOP_UP_BALANCE_PAGE);
                    return router;
                }

                boolean success = moneyAccountService.updateBalance(account, user, moneyAmount);
                if (success){
                    param += "&success=" + SUCCESS_MESSAGE;
                }

            }else {
                request.setAttribute(RequestAttribute.ERROR_TOP_UP_BALANCE, ERROR_INCORRECT_CARD_NUMBER);
                router.setPath(PagePath.TOP_UP_BALANCE_PAGE);
                return router;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
            return router;
        }

        router.setType(Router.Type.REDIRECT);
        router.setPath(PagePath.TO_PERSONAL_PAGE + param);
        return router;
    }
}
