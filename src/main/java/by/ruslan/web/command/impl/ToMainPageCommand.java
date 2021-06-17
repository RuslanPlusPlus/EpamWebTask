package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;
import by.ruslan.web.command.SessionAttribute;
import by.ruslan.web.model.entity.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, UserRole.GUEST);
        }
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_MAIN_PAGE);
        Router router = new Router(PagePath.MAIN_PAGE);
        return router;
    }
}
