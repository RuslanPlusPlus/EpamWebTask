package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.RequestAttribute;
import by.ruslan.web.command.Router;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToAddEventResultPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToAddEventResultPageCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try {
            RequestEditor.addEventToRequest(request, eventService, PagePath.TO_ADD_EVENT_RESULT_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        router.setPath(PagePath.ADD_EVENT_RESULT_PAGE);
        return router;
    }
}
