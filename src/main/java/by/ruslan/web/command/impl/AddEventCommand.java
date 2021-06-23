package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.EventException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.util.DateParser;
import by.ruslan.web.util.XssProtector;
import by.ruslan.web.validator.ParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventService eventService;
    private static final String SUCCESS_MESSAGE = "The event is successfully added!!!";
    private static final String ERROR_INCORRECT_NAME_FORMAT = "Event name incorrect format!!!";

    public AddEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, null);

        String eventName = request.getParameter(RequestParameter.EVENT_NAME);
        String dateAndTimeStr = request.getParameter(RequestParameter.DATETIME);
        String sportKindIdStr = request.getParameter(RequestParameter.SPORT_KIND_ID);
        String member1IdStr = request.getParameter(RequestParameter.MEMBER1_ID);
        String member2IdStr = request.getParameter(RequestParameter.MEMBER2_ID);

        if (!ParamValidator.isNameValid(eventName)){
            request.getSession().setAttribute(SessionAttribute.INPUT_INCORRECT_FORMAT, ERROR_INCORRECT_NAME_FORMAT);
            router.setType(Router.Type.REDIRECT);
            router.setPath(PagePath.TO_ADD_EVENT_PAGE);
            return router;
        }
        eventName = XssProtector.filterXss(eventName);

        Timestamp timestamp = DateParser.parseDate(dateAndTimeStr);
        long sportKindId = Long.parseLong(sportKindIdStr);
        long member1Id = Long.parseLong(member1IdStr);
        long member2Id = Long.parseLong(member2IdStr);

        try {
            Event event = eventService.add(eventName, sportKindId, member1Id, member2Id, timestamp);
            param += "&success=" + SUCCESS_MESSAGE;
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
            return router;
        } catch (EventException e) {
            logger.error(e.getErrorMessage());
            //request.setAttribute(RequestAttribute.ERROR, e.getErrorMessage());
            router.setPath(PagePath.TO_BOOKMAKER_PAGE);
            param += "&error=" + e.getErrorMessage();
        }

        router.setType(Router.Type.REDIRECT);
        router.setPath(PagePath.TO_BOOKMAKER_PAGE + param);
        return router;
    }
}
