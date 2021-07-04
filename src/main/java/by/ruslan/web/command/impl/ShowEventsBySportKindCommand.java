package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code ShowEventsBySportKindCommand} class is responsible for showing
 * events filtered by kind of sport.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ShowEventsBySportKindCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventService eventService;
    private final SportKindService sportKindService;

    public ShowEventsBySportKindCommand(EventService eventService, SportKindService sportKindService){
        this.eventService = eventService;
        this.sportKindService = sportKindService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String kindIdStr = request.getParameter(RequestParameter.SPORT_KIND_ID);
        long kindId = Long.parseLong(kindIdStr);
        try {
            RequestEditor.addSportKindsToRequest(request, sportKindService);
            List<Event> events = eventService.findActiveEventsBySportKind(kindId);
            request.setAttribute(RequestAttribute.EVENTS, events);
            router.setPath(PagePath.MAIN_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.SHOW_EVENTS_BY_SPORT_KIND + "&sportKindId=" + kindIdStr);
        return router;
    }
}
