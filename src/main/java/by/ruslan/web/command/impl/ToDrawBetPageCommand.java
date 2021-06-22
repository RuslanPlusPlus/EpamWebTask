package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToDrawBetPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToDrawBetPageCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        try {
            RequestEditor.addEventToRequest(request, eventService, PagePath.TO_DRAW_BET_PAGE);
            if (request.getParameter(RequestParameter.SUCCESS) != null){
                request.setAttribute(RequestAttribute.SUCCESS, request.getParameter(RequestParameter.SUCCESS));
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        router.setPath(PagePath.DRAW_BET_PAGE);
        return router;
    }
}
