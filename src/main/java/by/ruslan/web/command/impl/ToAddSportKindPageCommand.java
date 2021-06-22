package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;
import by.ruslan.web.command.SessionAttribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAddSportKindPageCommand implements Command {

    static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        router.setPath(PagePath.ADD_SPORT_KIND_PAGE);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_ADD_SPORT_KIND_PAGE);
        return router;
    }
}
