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
    CHANGE_LOCALE{
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    TO_REGISTER_PAGE {
        {
            this.command = new ToRegisterPageCommand();
        }
    },
    TO_LOGIN_PAGE{
        {
            this.command = new ToLoginPageCommand();
        }
    },
    TO_MAIN_PAGE{
        {
            this.command = new ToMainPageCommand();
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
