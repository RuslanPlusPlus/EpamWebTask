package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code ChangeRoleCommand} class is responsible for login out.
 *
 * @author Ruslan Nedvedskiy
 * @see Command
 */

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.INDEX_JSP, Router.Type.REDIRECT);
        HttpSession session = request.getSession();
        session.invalidate();
        return router;
    }
}
