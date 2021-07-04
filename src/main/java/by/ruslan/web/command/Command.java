package by.ruslan.web.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface represents command.
 *
 * @author Ruslan Nedvedskiy
 */

public interface Command {

    /**
     * Executes command.
     *
     * @param request request
     * @return Router containing page path and redirect type
     */

    Router execute(HttpServletRequest request);
}
