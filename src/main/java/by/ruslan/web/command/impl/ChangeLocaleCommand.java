package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import by.ruslan.web.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeLocaleCommand implements Command {

    static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String language = request.getParameter(RequestParameter.LANGUAGE);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        logger.debug("Current page: " + page);
        session.setAttribute(SessionAttribute.LOCALE, language);
        Router router = new Router(page);
        router.setType(Router.Type.REDIRECT);
        return router;
    }
}
