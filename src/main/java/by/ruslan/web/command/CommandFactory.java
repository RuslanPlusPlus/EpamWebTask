package by.ruslan.web.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CommandFactory {

    static final Logger logger = LogManager.getLogger();
    static final String PARAMETER = "command";

    public static Optional<Command> defineCommand(HttpServletRequest request){
        Command command;
        String action = request.getParameter(PARAMETER);
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
