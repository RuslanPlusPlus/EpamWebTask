package by.ruslan.web.controller.filter;

import by.ruslan.web.command.CommandType;

import java.util.Set;

public enum RoleAccessType {
    GUEST(Set.of(
            CommandType.SIGN_IN,
            CommandType.SIGN_UP,
            CommandType.TO_MAIN_PAGE,
            CommandType.CHANGE_LOCALE,
            CommandType.TO_EVENT_PAGE,
            CommandType.TO_LOGIN_PAGE,
            CommandType.TO_REGISTER_PAGE,
            CommandType.SHOW_EVENTS_BY_SPORT_KIND
    )),
    CLIENT(Set.of(
            CommandType.TO_MAIN_PAGE,
            CommandType.TO_PERSONAL_PAGE,
            CommandType.CHANGE_LOCALE,
            CommandType.LOGOUT,
            CommandType.TO_EVENT_PAGE,
            CommandType.TO_MAKE_BET_PAGE,
            CommandType.TO_WIN_BET_PAGE,
            CommandType.TO_EXACT_SCORE_BET_PAGE,
            CommandType.TO_DRAW_BET_PAGE,
            CommandType.MAKE_WIN_BET,
            CommandType.MAKE_DRAW_BET,
            CommandType.MAKE_EXACT_SCORE_BET

    )),
    ADMIN(Set.of(
            CommandType.TO_MAIN_PAGE,
            CommandType.TO_ADMIN_PAGE,
            CommandType.TO_PERSONAL_PAGE,
            CommandType.TO_USERS_PAGE,
            CommandType.CHANGE_ROLE,
            CommandType.CHANGE_LOCALE,
            CommandType.LOGOUT,
            CommandType.TO_EVENT_PAGE,
            CommandType.TO_MAKE_BET_PAGE,
            CommandType.TO_WIN_BET_PAGE,
            CommandType.TO_EXACT_SCORE_BET_PAGE,
            CommandType.TO_DRAW_BET_PAGE,
            CommandType.MAKE_WIN_BET,
            CommandType.MAKE_DRAW_BET,
            CommandType.MAKE_EXACT_SCORE_BET
    )),
    BOOKMAKER(Set.of(
            CommandType.TO_MAIN_PAGE,
            CommandType.TO_PERSONAL_PAGE,
            CommandType.TO_ADD_EVENT_RESULT_PAGE,
            CommandType.CHANGE_LOCALE,
            CommandType.LOGOUT,
            CommandType.TO_EVENT_PAGE,
            CommandType.TO_MAKE_BET_PAGE,
            CommandType.TO_WIN_BET_PAGE,
            CommandType.TO_EXACT_SCORE_BET_PAGE,
            CommandType.TO_DRAW_BET_PAGE,
            CommandType.MAKE_WIN_BET,
            CommandType.MAKE_DRAW_BET,
            CommandType.MAKE_EXACT_SCORE_BET
    ));

    // TODO: 20.06.2021 BOOKMAKER commands

    RoleAccessType(Set<CommandType> allowedCommands){
        this.allowedCommands = allowedCommands;
    }

    private Set<CommandType> allowedCommands;

    public Set<CommandType> getAllowedCommands() {
        return allowedCommands;
    }
}
