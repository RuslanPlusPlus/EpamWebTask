package by.ruslan.web.command;

import by.ruslan.web.command.impl.DisplayCommand;
import by.ruslan.web.model.service.impl.UserServiceImpl;

public enum CommandType {
    DISPLAY_USER_LIST{
        {
            this.command = new DisplayCommand(new UserServiceImpl());
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
