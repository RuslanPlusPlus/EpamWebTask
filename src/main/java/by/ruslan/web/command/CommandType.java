package by.ruslan.web.command;

import by.ruslan.web.command.impl.AgeRangeCommand;
import by.ruslan.web.command.impl.DisplayCommand;
import by.ruslan.web.command.impl.SortCommand;
import by.ruslan.web.service.impl.UserServiceImpl;

public enum CommandType {
    DISPLAY_USER_LIST{
        {
            this.command = new DisplayCommand(new UserServiceImpl());
        }
    },
    SORT_USER_LIST{
        {
            this.command = new SortCommand(new UserServiceImpl());
        }
    },
    DISPLAY_BY_AGE_RANGE{
        {
            this.command = new AgeRangeCommand(new UserServiceImpl());
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
