package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code ToBookmakerPageCommand} class is responsible for redirecting
 * to bookmaker page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToBookmakerPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        if (request.getParameter(RequestParameter.SUCCESS) != null){
            request.setAttribute(RequestAttribute.SUCCESS, request.getParameter(RequestParameter.SUCCESS));
        }
        if (request.getParameter(RequestParameter.ERROR) != null){
            request.setAttribute(RequestAttribute.ERROR, request.getParameter(RequestParameter.ERROR));
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_BOOKMAKER_PAGE);
        router.setPath(PagePath.BOOKMAKER_PAGE);
        return router;
    }
}
