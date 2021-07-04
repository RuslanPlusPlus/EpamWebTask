package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.EventResultException;
import by.ruslan.web.model.entity.Bet;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.service.BetService;
import by.ruslan.web.model.service.EventResultService;
import by.ruslan.web.model.service.UserService;
import by.ruslan.web.util.XssProtector;
import by.ruslan.web.validator.ParamValidator;
import com.google.protobuf.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * The {@code AddEventResultCommand} class is responsible for adding event result.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class AddEventResultCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventResultService eventResultService;
    private static final String SUCCESS_MESSAGE = "The event result is successfully added!!!";
    private static final String ERROR_INCORRECT_SCORE_FORMAT = "Score incorrect format!!!";

    public AddEventResultCommand(EventResultService eventResultService){
        this.eventResultService = eventResultService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, null);

        EventResult eventResult = new EventResult();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        String winnerIdStr = request.getParameter(RequestParameter.WINNER_ID);
        String loserIdStr = request.getParameter(RequestParameter.LOSER_ID);
        String winnerScoreStr = request.getParameter(RequestParameter.WINNER_SCORE);
        String loserScoreStr = request.getParameter(RequestParameter.LOSER_SCORE);

        if (!ParamValidator.isScoreValid(winnerScoreStr) || !ParamValidator.isScoreValid(loserScoreStr)){
            request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_SCORE_FORMAT);
            router.setType(Router.Type.REDIRECT);
            router.setPath(PagePath.TO_ADD_EVENT_RESULT_PAGE + "&eventId=" + eventIdStr);
            return router;
        }
        winnerScoreStr = XssProtector.filterXss(winnerScoreStr);
        loserScoreStr = XssProtector.filterXss(loserScoreStr);

        long eventId = Long.parseLong(eventIdStr);
        long winnerId = Long.parseLong(winnerIdStr);
        long loserId = Long.parseLong(loserIdStr);
        int winnerScore = Integer.parseInt(winnerScoreStr);
        int loserScore = Integer.parseInt(loserScoreStr);
        eventResult.setEventId(eventId);
        eventResult.setWinnerId(winnerId);
        eventResult.setLoserId(loserId);
        eventResult.setWinnerScore(winnerScore);
        eventResult.setLoserScore(loserScore);

        try {
            eventResultService.add(eventResult);
            param += "&success=" + SUCCESS_MESSAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            return router;
        } catch (EventResultException e) {
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
