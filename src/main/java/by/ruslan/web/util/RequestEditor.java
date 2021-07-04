package by.ruslan.web.util;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.model.service.impl.SportKindServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code RequestEditor} class is responsible for
 * adding Entity object to request attributes
 *
 * @author Ruslan Nedvedskiy
 */
public class RequestEditor {

    public static void addSportKindsToRequest(HttpServletRequest request, SportKindService sportKindService) throws ServiceException {
        List<SportKind> sportKinds = sportKindService.findAll();
        //logger.debug(sportKinds);
        request.setAttribute(RequestAttribute.SPORT_KINDS, sportKinds);
    }

    public static void addEventToRequest(HttpServletRequest request, EventService eventService, String servletPage) throws ServiceException {
        HttpSession session = request.getSession();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        long eventId = Long.parseLong(eventIdStr);
        Optional<Event> eventOptional = eventService.findEventById(eventId);
        request.setAttribute(RequestAttribute.EVENT, eventOptional.get());
        session.setAttribute(SessionAttribute.CURRENT_PAGE, servletPage+ "&eventId=" + eventIdStr);
    }
}
