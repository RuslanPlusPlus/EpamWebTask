package by.ruslan.web.command;

import by.ruslan.web.command.impl.*;
import by.ruslan.web.model.service.impl.EventServiceImpl;
import by.ruslan.web.model.service.impl.SportKindServiceImpl;
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
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    CHANGE_LOCALE{
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    CHANGE_ROLE{
        {
            this.command = new ChangeRoleCommand(new UserServiceImpl());
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
            this.command = new ToMainPageCommand(new EventServiceImpl(), new SportKindServiceImpl());
        }
    },
    TO_PERSONAL_PAGE{
        {
            this.command = new ToPersonalPageCommand();
        }
    },
    TO_ADMIN_PAGE{
        {
            this.command = new ToAdminPageCommand();
        }
    },
    TO_BOOKMAKER_PAGE{
        {
            this.command = new ToBookmakerPageCommand();
        }
    },
    TO_USERS_PAGE{
        {
            this.command = new ToUsersPageCommand(new UserServiceImpl());
        }
    },
    TO_EVENT_PAGE{
        {
            this.command = new ToEventPageCommand(new EventServiceImpl());
        }
    },
    SHOW_EVENTS_BY_SPORT_KIND{
        {
            this.command = new ShowEventsBySportKindCommand(new EventServiceImpl(), new SportKindServiceImpl());
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
