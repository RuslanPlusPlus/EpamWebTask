package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.Event;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.service.EventService;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ToWinBetPageCommand} class is responsible for redirecting
 * to win bet page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToWinBetPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private EventService eventService;

    public ToWinBetPageCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try {
            RequestEditor.addEventToRequest(request, eventService, PagePath.TO_WIN_BET_PAGE);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }
        router.setPath(PagePath.WIN_BET_PAGE);
        return router;
    }
}
