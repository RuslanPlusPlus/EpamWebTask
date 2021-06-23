package by.ruslan.web.command.impl;

import by.ruslan.web.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToTopUpBalancePageCommand implements Command {

    static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_TOP_UP_BALANCE_PAGE);
        Router router = new Router(PagePath.TOP_UP_BALANCE_PAGE);
        return router;
    }
}
