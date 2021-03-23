package by.ruslan.web.command.factory;

import by.ruslan.web.command.Command;
import by.ruslan.web.command.CommandType;
import by.ruslan.web.command.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {

    static final Logger logger = LogManager.getLogger();
    static final String PARAMETER = "command";

    public static Command defineCommand(HttpServletRequest request){
        Command command = null;
        String action = request.getParameter(PARAMETER);
        if (action == null || action.isBlank()){
            return new EmptyCommand();
        }
        try {
            CommandType commandType = CommandType.valueOf(action.toUpperCase());
            command = commandType.getCommand();
            logger.info("Command class: " + command.getClass());
        }
        catch (IllegalArgumentException e){
            command = new EmptyCommand();
        }
        return command;
    }
}
