package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code ToExactScoreBetPageCommand} class is responsible for redirecting
 * to exact score bet page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToExactScoreBetPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToExactScoreBetPageCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String eventIdStr = request.getParameter(RequestParameter.EVENT_ID);
        try {
            RequestEditor.addEventToRequest(request, eventService, PagePath.TO_EXACT_SCORE_BET_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        router.setPath(PagePath.EXACT_SCORE_BET_PAGE);
        return router;
    }
}
