package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;
import by.ruslan.web.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code ToRegisterPageCommand} class is responsible for redirecting
 * to register page.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class ToRegisterPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, PagePath.TO_REGISTER_PAGE);
        Router router = new Router(PagePath.SIGN_UP);
        return router;
    }
}
