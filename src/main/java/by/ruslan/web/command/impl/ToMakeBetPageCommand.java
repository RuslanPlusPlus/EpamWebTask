package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.SportKindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code ToMakeBetPageCommand} class is responsible for redirecting
 * to make bet page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToMakeBetPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventService eventService;

    public ToMakeBetPageCommand(EventService eventService) {
        this.eventService = eventService;
    }
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        //request.setAttribute(RequestAttribute.EVENT_ID, eventIdStr);
        long eventId = Long.parseLong(eventIdStr);
        try {
            Optional<Event> event = eventService.findEventById(eventId);
            request.setAttribute(RequestAttribute.EVENT, event.get());

        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        router.setPath(PagePath.MAKE_BET_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_MAKE_BET_PAGE + "&eventId=" + eventIdStr);
        return router;
    }
}
