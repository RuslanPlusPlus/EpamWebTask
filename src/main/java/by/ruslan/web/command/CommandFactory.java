package by.ruslan.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The {@code CommandFactory} class represents command provider.
 *
 * @author Ruslan Nedvedskiy
 */

public class CommandFactory {

    static final Logger logger = LogManager.getLogger();

    /**
     * Define command by input type of request
     *
     * @param request request
     * @return the optional of created command
     */

    public static Optional<Command> defineCommand(HttpServletRequest request){
        Command command;
        String action = request.getParameter(RequestParameter.COMMAND);
        if (action == null || action.isBlank()){
            return Optional.empty();
        }
        try {
            CommandType commandType = CommandType.valueOf(action.toUpperCase());
            command = commandType.getCommand();
            logger.debug("Command class: " + command.getClass());
        }
        catch (IllegalArgumentException e){
            return Optional.empty();
        }
        return Optional.of(command);
    }
}
