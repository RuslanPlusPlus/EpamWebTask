package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.entity.UserRole;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.model.service.impl.EventServiceImpl;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToMainPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventService eventService;
    private final SportKindService sportKindService;

    public ToMainPageCommand(EventService eventService, SportKindService sportKindService){
        this.eventService = eventService;
        this.sportKindService = sportKindService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, UserRole.GUEST);
        }
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, SessionAttribute.ENGLISH);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_MAIN_PAGE);
        try {
            RequestEditor.addSportKindsToRequest(request, sportKindService);
            List<Event> events = eventService.findActiveEventsSortedByDate();
            request.setAttribute(RequestAttribute.EVENTS, events);
            router.setPath(PagePath.MAIN_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
        }
        router = new Router(PagePath.MAIN_PAGE);
        return router;
    }
}
