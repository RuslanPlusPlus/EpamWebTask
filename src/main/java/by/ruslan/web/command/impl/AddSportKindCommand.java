package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.exception.ServiceException;
import by.ruslan.web.exception.SportKindException;
import by.ruslan.web.model.service.SportKindService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddSportKindCommand implements Command {

    static final Logger logger = LogManager.getLogger();
    private final SportKindService sportKindService;
    private static final String SUCCESS_MESSAGE = "The sport kind is successfully added!!!";

    public AddSportKindCommand(SportKindService sportKindService){
        this.sportKindService = sportKindService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String param = new String();
        String kindName = request.getParameter(RequestParameter.SPORT_KIND_NAME);

        try {
            boolean success = sportKindService.add(kindName);
            if(success){
                param += "&success=" + SUCCESS_MESSAGE;
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
            request.setAttribute(RequestAttribute.ERROR, e.getMessage());
            router.setPath(PagePath.ERROR_500);
            return router;
        } catch (SportKindException e) {
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
