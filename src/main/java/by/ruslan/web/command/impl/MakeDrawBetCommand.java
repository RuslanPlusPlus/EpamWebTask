package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.BetService;
import by.ruslan.web.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class MakeDrawBetCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final BetService betService;
    private final UserService userService;
    private static final String SUCCESS_MESSAGE = "The bet is successfully made!!!";

    public MakeDrawBetCommand(BetService betService, UserService userService){
        this.betService = betService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        //HttpSession session = request.getSession();
        Router router = new Router();

        Bet.BetType betType = Bet.BetType.DRAW;
        String moneyStr = request.getParameter(RequestParameter.MONEY);
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(moneyStr));
        long eventId = Long.parseLong(eventIdStr);
        long userId = Long.parseLong(userIdStr);

        Bet bet = new Bet();
        bet.setType(betType);
        bet.setMoney(money);
        bet.setUserId(userId);
        bet.setEventId(eventId);
        //logger.debug(bet);

        try {
            User user = userService.findByUserId(userId).get();
            boolean success = betService.makeRate(bet, user);
            if (success){
                request.setAttribute(RequestAttribute.SUCCESS, SUCCESS_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        } catch (BetException e) {
            logger.error(e.getErrorMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getErrorMessage());
            router.setPath(PagePath.TO_EVENT_PAGE);
        }

        router.setType(Router.Type.REDIRECT);
        router.setPath(PagePath.TO_EVENT_PAGE + "&eventId=" + eventIdStr + "&success=" + SUCCESS_MESSAGE);
        return router;
    }
}
