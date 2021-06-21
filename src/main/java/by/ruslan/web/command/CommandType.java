package by.ruslan.web.command;

import by.ruslan.web.command.impl.*;
import by.ruslan.web.model.service.impl.*;

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
    MAKE_WIN_BET{
        {
            this.command = new MakeWinBetCommand(new BetServiceImpl(), new UserServiceImpl());
        }
    },
    MAKE_DRAW_BET{
        {
            this.command = new MakeDrawBetCommand(new BetServiceImpl(), new UserServiceImpl());
        }
    },
    MAKE_EXACT_SCORE_BET{
        {
            this.command = new MakeExactScoreBetCommand(new BetServiceImpl(), new UserServiceImpl(), new EventServiceImpl());
        }
    },
    ADD_EVENT_RESULT{
        {
            this.command = new AddEventResultCommand(new EventResultServiceImpl());
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
    },
    TO_MAKE_BET_PAGE{
        {
            this.command = new ToMakeBetPageCommand(new EventServiceImpl());
        }
    },
    TO_DRAW_BET_PAGE{
        {
            this.command = new ToDrawBetPageCommand(new EventServiceImpl());
        }
    },
    TO_WIN_BET_PAGE{
        {
            this.command = new ToWinBetPageCommand(new EventServiceImpl());
        }
    },
    TO_EXACT_SCORE_BET_PAGE{
        {
            this.command = new ToExactScoreBetPageCommand(new EventServiceImpl());
        }
    },
    TO_ADD_EVENT_RESULT_PAGE{
        {
            this.command = new ToAddEventResultPageCommand(new EventServiceImpl());
        }
    };
    Command command;

    public Command getCommand() {
        return command;
    }
}
