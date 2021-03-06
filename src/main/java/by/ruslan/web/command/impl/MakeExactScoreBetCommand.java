package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.BetException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.entity.User;
import by.ruslan.web.model.service.BetService;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.UserService;
import by.ruslan.web.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * The {@code MakeExactScoreBetCommand} class is responsible for making exact score bet.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class MakeExactScoreBetCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final BetService betService;
    private final UserService userService;
    private final EventService eventService;
    private static final String SUCCESS_MESSAGE = "The bet is successfully made!!!";
    private static final String ERROR_INCORRECT_MONEY_FORMAT = "Money incorrect format!!!";
    private static final String ERROR_INCORRECT_SCORE_FORMAT = "Score incorrect format!!!";

    public MakeExactScoreBetCommand(BetService betService, UserService userService, EventService eventService){
        this.betService = betService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, null);
        Bet.BetType betType = Bet.BetType.EXACT_SCORE;
        String moneyStr = request.getParameter(RequestParameter.MONEY);
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String userIdStr = request.getParameter(RequestParameter.USER_ID);

        if (!ParamValidator.isMoneyAmountValid(moneyStr)){
            request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_MONEY_FORMAT);
            router.setType(Router.Type.REDIRECT);
            //request.setAttribute(RequestAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_MONEY_FORMAT);
            router.setPath(PagePath.TO_EXACT_SCORE_BET_PAGE);
            return router;
        }

        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(moneyStr));
        long eventId = Long.parseLong(eventIdStr);
        long userId = Long.parseLong(userIdStr);
        int counter = 0;
        long member1Id = 0;
        long member2Id = 0;
        int member1Score = 0;
        int member2Score = 0;
        try {
            Event event = eventService.findEventById(eventId).get();
            List<EventMember> members = event.getMembers();
            for (EventMember member: members){
                String paramName = String.valueOf(member.getMemberId());
                String memberScoreStr = request.getParameter(paramName);
                if (!ParamValidator.isScoreValid(memberScoreStr)){
                    request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_SCORE_FORMAT);
                    router.setType(Router.Type.REDIRECT);
                    //request.setAttribute(RequestAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_SCORE_FORMAT);
                    router.setPath(PagePath.TO_EXACT_SCORE_BET_PAGE);
                    return router;
                }
                int memberScore = Integer.parseInt(memberScoreStr);
                if (counter == 0){
                    member1Id = member.getMemberId();
                    member1Score = memberScore;
                }else {
                    member2Id = member.getMemberId();
                    member2Score = memberScore;
                }
                counter ++;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            return router;
        }

        Bet bet = new Bet();
        bet.setType(betType);
        bet.setMember1Id(member1Id);
        bet.setMember1Score(member1Score);
        bet.setMember2Id(member2Id);
        bet.setMember2Score(member2Score);
        bet.setMoney(money);
        bet.setUserId(userId);
        bet.setEventId(eventId);
        logger.debug(bet);

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
            //request.setAttribute(RequestAttribute.ERROR, e.getErrorMessage());
            router.setPath(PagePath.TO_EVENT_PAGE);
            param += "&error=" + e.getErrorMessage();
        }

        router.setType(Router.Type.REDIRECT);
        param += "&eventId=" + eventIdStr;
        router.setPath(PagePath.TO_EVENT_PAGE + param);
        return router;
    }
}
