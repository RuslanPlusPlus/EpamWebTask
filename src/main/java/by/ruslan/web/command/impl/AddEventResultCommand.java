package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.EventResultException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.service.BetService;
import by.ruslan.web.model.service.EventResultService;
import by.ruslan.web.model.service.UserService;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddEventResultCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventResultService eventResultService;

    public AddEventResultCommand(EventResultService eventResultService){
        this.eventResultService = eventResultService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        EventResult eventResult = new EventResult();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String winnerIdStr = request.getParameter(RequestParameter.WINNER_ID);
        String loserIdStr = request.getParameter(RequestParameter.LOSER_ID);
        String winnerScoreStr = request.getParameter(RequestParameter.WINNER_SCORE);
        String loserScoreStr = request.getParameter(RequestParameter.LOSER_SCORE);

        long eventId = Long.parseLong(eventIdStr);
        long winnerId = Long.parseLong(winnerIdStr);
        long loserId = Long.parseLong(loserIdStr);
        // TODO: 21.06.2021 validation
        int winnerScore = Integer.parseInt(winnerScoreStr);
        int loserScore = Integer.parseInt(loserScoreStr);
        eventResult.setEventId(eventId);
        eventResult.setWinnerId(winnerId);
        eventResult.setLoserId(loserId);
        eventResult.setWinnerScore(winnerScore);
        eventResult.setLoserScore(loserScore);
        logger.debug(eventResult);

        try {
            eventResultService.add(eventResult);
            //request.setAttribute(RequestAttribute.SUCCESS, SUCCESS_MESSAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        } catch (EventResultException e) {
            logger.error(e.getErrorMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getErrorMessage());
            router.setPath(PagePath.TO_EVENT_PAGE);
        }

        router.setType(Router.Type.REDIRECT);
        router.setPath(PagePath.TO_EVENT_PAGE + "&eventId=" + eventIdStr +
                "&success=" + '1');
        return router;
    }
}
