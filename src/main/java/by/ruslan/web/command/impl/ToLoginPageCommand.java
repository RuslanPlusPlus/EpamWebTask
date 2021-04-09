package by.ruslan.web.command.impl;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.PagePath;
import by.ruslan.web.command.Router;

import javax.servlet.http.HttpServletRequest;

public class ToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.SIGN_IN, Router.Type.FORWARD);
        return router;
    }
}
