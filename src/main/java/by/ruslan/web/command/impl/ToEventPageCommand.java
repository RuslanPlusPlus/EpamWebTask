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

public class ToEventPageCommand implements Command {

    private EventService eventService;
    static final Logger logger = LogManager.getLogger();

    public ToEventPageCommand(EventService eventService){
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        long eventId = Long.parseLong(eventIdStr);
        try {
            Optional<Event> event = eventService.findEventById(eventId);
            if (event.isPresent()){
                //List<EventMember> members = event.get().getMembers();
                request.setAttribute(RequestAttribute.EVENT, event.get());
            }else {
                logger.error("Failed to find event by id");
            }
            router.setPath(PagePath.EVENT_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_EVENT_PAGE + "&eventId=" + eventIdStr);
        return router;
    }
}
