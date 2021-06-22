package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.MemberException;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.service.EventMemberService;
import by.ruslan.web.model.service.SportKindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddMemberCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final EventMemberService eventMemberService;
    private static final String SUCCESS_MESSAGE = "The member is successfully added!!!";

    public AddMemberCommand(EventMemberService eventMemberService){
        this.eventMemberService = eventMemberService;
    }
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        String kindName = request.getParameter(RequestParameter.MEMBER_NAME);
        String kindIdStr = request.getParameter(RequestParameter.SPORT_KIND_ID);
        long kindId = Long.parseLong(kindIdStr);

        try {
            boolean success = eventMemberService.add(kindName, kindId);
            if(success){
                param += "&success=" + SUCCESS_MESSAGE;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
            return router;
        } catch (MemberException e) {
            logger.error(e.getErrorMessage());
            //request.setAttribute(RequestAttribute.ERROR, e.getErrorMessage());
            router.setPath(PagePath.TO_BOOKMAKER_PAGE);
            param += "&error=" + e.getErrorMessage();
        }

        router.setType(Router.Type.REDIRECT);
        router.setPath(PagePath.TO_BOOKMAKER_PAGE + param);
        return router;
    }
}
