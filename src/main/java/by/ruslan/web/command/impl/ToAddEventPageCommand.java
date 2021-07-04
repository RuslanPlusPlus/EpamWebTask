package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.EventMember;
import by.ruslan.web.model.entity.EventResult;
import by.ruslan.web.model.service.EventMemberService;
import by.ruslan.web.model.service.SportKindService;
import by.ruslan.web.model.service.impl.SportKindServiceImpl;
import by.ruslan.web.util.RequestEditor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code ToAddEventPageCommand} class is responsible for redirecting
 * to add event page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToAddEventPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final SportKindService sportKindService;
    private final EventMemberService eventMemberService;

    public ToAddEventPageCommand(SportKindServiceImpl sportKindService, EventMemberService eventMemberService){
        this.sportKindService = sportKindService;
        this.eventMemberService = eventMemberService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        try {
            RequestEditor.addSportKindsToRequest(request, sportKindService);
            List<EventMember> eventMembers = eventMemberService.findAll();
            request.setAttribute(RequestAttribute.MEMBERS, eventMembers);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
        }
        router.setPath(PagePath.ADD_EVENT_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_ADD_EVENT_PAGE);
        return router;
    }
}
