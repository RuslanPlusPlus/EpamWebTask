package by.ruslan.web.command;

import by.ruslan.web.command.impl.*;
import by.ruslan.web.model.service.impl.UserServiceImpl;

public enum CommandType {
    FIND_ALL_USERS{
        {
            this.command = new FindAllUsersCommand(new UserServiceImpl());
        }
    },
    FIND_USER_BY_EMAIL{
        {
            this.command = new FindByEmailCommand(new UserServiceImpl());
        }
    },
    SIGN_IN{
        {
            this.command = new SignInCommand(new UserServiceImpl());
        }
    },
    SIGN_UP{
        {
            this.command = new SignUpCommand(new UserServiceImpl());
        }
    },
    NOT_REGISTERED{
        {
            this.command = new NotRegisteredCommand();
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
