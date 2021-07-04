package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ToEventPageCommand} class is responsible for redirecting
 * to event page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToEventPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToEventPageCommand(EventService eventService){
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        if (eventIdStr != null){
            long eventId = Long.parseLong(eventIdStr);
            try {
                Optional<Event> event = eventService.findEventById(eventId);
                if (event.isPresent()){
                    //List<EventMember> members = event.get().getMembers();
                    request.setAttribute(RequestAttribute.EVENT, event.get());
                    if (request.getParameter(RequestParameter.SUCCESS) != null){
                        request.setAttribute(RequestAttribute.SUCCESS, request.getParameter(RequestParameter.SUCCESS));
                    }
                    if (request.getParameter(RequestParameter.ERROR) != null){
                        request.setAttribute(RequestAttribute.ERROR, request.getParameter(RequestParameter.ERROR));
                    }
                }else {
                    logger.error("Failed to find event by id");
                }
                router.setPath(PagePath.EVENT_PAGE);
            } catch (ServiceException e) {
                logger.error(e.getMessage());
                router.setPath(PagePath.ERROR_500);
                request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            }
        }else {
            router.setPath(PagePath.EVENT_PAGE);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_EVENT_PAGE);
        return router;
    }
}
