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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class MakeExactScoreBetCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final BetService betService;
    private final UserService userService;
    private final EventService eventService;
    private static final String SUCCESS_MESSAGE = "The bet is successfully made!!!";

    public MakeExactScoreBetCommand(BetService betService, UserService userService, EventService eventService){
        this.betService = betService;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        Bet.BetType betType = Bet.BetType.EXACT_SCORE;
        String moneyStr = request.getParameter(RequestParameter.MONEY);
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String userIdStr = request.getParameter(RequestParameter.USER_ID);
        //String memberIdStr = request.getParameter(RequestParameter.MEMBER_ID);
        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(moneyStr));
        long eventId = Long.parseLong(eventIdStr);
        long userId = Long.parseLong(userIdStr);
        int counter = 0;
        long member1Id = 0;
        long member2Id = 0;
        int member1Score = 0;
        int member2Score = 0;
        //long memberId = Long.parseLong(memberIdStr);
        try {
            Event event = eventService.findEventById(eventId).get();
            List<EventMember> members = event.getMembers();
            for (EventMember member: members){
                String paramName = String.valueOf(member.getMemberId());
                String memberScoreStr = request.getParameter(paramName);
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

        // TODO: 21.06.2021 redirect
        router.setPath(PagePath.TO_EVENT_PAGE);
        return router;
    }
}
