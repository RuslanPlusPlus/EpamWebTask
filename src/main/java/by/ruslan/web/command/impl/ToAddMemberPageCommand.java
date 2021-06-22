package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.model.entity.SportKind;
import by.ruslan.web.model.service.SportKindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAddMemberPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private SportKindService sportKindService;

    public ToAddMemberPageCommand(SportKindService sportKindService){
        this.sportKindService = sportKindService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();

        try {
            List<SportKind> sportKinds = sportKindService.findAll();
            request.setAttribute(RequestAttribute.SPORT_KINDS, sportKinds);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            router.setPath(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
        }

        router.setPath(PagePath.ADD_MEMBER_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_ADD_MEMBER_PAGE);
        return router;
    }
}
