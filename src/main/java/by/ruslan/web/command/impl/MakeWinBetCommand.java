package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.BetService;
import by.ruslan.web.model.service.UserService;
import by.ruslan.web.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * The {@code MakeWinBetCommand} class is responsible for making win bet.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class MakeWinBetCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final BetService betService;
    private final UserService userService;
    private static final String SUCCESS_MESSAGE = "The bet is successfully made!!!";
    private static final String ERROR_INCORRECT_MONEY_FORMAT = "Money incorrect format!!!";

    public MakeWinBetCommand(BetService betService, UserService userService){
        this.userService = userService;
        this.betService = betService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        //HttpSession session = request.getSession();
        Router router = new Router();
        String param = new String();
        request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, null);

        Bet.BetType betType = Bet.BetType.WIN;
        String moneyStr = request.getParameter(RequestParameter.MONEY);
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        String memberIdStr = request.getParameter(RequestParameter.MEMBER_ID);

        if (!ParamValidator.isMoneyAmountValid(moneyStr)){
            request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_MONEY_FORMAT);
            router.setType(Router.Type.REDIRECT);
            //request.setAttribute(RequestAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_MONEY_FORMAT);
            router.setPath(PagePath.TO_WIN_BET_PAGE);
            return router;
        }

        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(moneyStr));
        long eventId = Long.parseLong(eventIdStr);
        long userId = Long.parseLong(userIdStr);
        long memberId = Long.parseLong(memberIdStr);

        Bet bet = new Bet();
        bet.setType(betType);
        bet.setMember1Id(memberId);
        bet.setMoney(money);
        bet.setUserId(userId);
        bet.setEventId(eventId);

        try {
            User user = userService.findByUserId(userId).get();
            boolean success = betService.makeRate(bet, user);
            if (success){
                param += "&success=" + SUCCESS_MESSAGE;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            return router;
        } catch (BetException e) {
            logger.error(e.getErrorMessage());
            router.setPath(PagePath.TO_EVENT_PAGE);
            param += "&error=" + e.getErrorMessage();
        }

        router.setType(Router.Type.REDIRECT);
        param += "&eventId=" + eventIdStr;
        router.setPath(PagePath.TO_EVENT_PAGE + param);
        return router;
    }
}
