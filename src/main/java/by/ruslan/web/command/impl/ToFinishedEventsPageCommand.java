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
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ToFinishedEventsPageCommand} class is responsible for redirecting
 * to finished events page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToFinishedEventsPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;
    private final String MARK_SYMBOL = " ";

    public ToFinishedEventsPageCommand(EventService eventService){
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();

        try {
            List<Event> allEvents = eventService.findActiveEvents();
            List<Event> eventsToAddResult = new ArrayList<>();
            for (Event event: allEvents){
                Event event1 = eventService.findEventById(event.getEventId()).get();
                if (event1.isReadyToAddResult()){
                    eventsToAddResult.add(event1);
                }
            }
            if (eventsToAddResult.size() == 0){
                request.setAttribute(RequestAttribute.NO_FINISHED_EVENTS, MARK_SYMBOL);
            }else {
                request.setAttribute(RequestAttribute.EVENTS, eventsToAddResult);
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }

        router.setPath(PagePath.FINISHED_EVENTS_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_FINISHED_EVENTS_PAGE);
        return router;
    }
}
