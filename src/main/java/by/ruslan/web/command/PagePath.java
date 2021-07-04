package by.ruslan.web.command;

/**
 * The {@code PagePath} class contains all page paths.
 *
 * @author Ruslan Nedvedskiy
 */

public final class PagePath {
    public static final String INDEX_JSP = "/index.jsp";
    public static final String MAIN_PAGE = "/pages/main.jsp";
    public static final String SIGN_IN = "/pages/signin.jsp";
    public static final String SIGN_UP = "/pages/signup.jsp";
    public static final String ADMIN_PAGE = "/pages/admin.jsp";
    public static final String EVENT_PAGE = "/pages/event.jsp";
    public static final String BOOKMAKER_PAGE = "/pages/bookmakerPage.jsp";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String MAKE_BET_PAGE = "/pages/makeBetPage.jsp";
    public static final String WIN_BET_PAGE = "/pages/winBet.jsp";
    public static final String DRAW_BET_PAGE = "/pages/drawBet.jsp";
    public static final String EXACT_SCORE_BET_PAGE = "/pages/exactScoreBet.jsp";
    public static final String ADD_EVENT_RESULT_PAGE = "/pages/addEventResult.jsp";
    public static final String ADD_EVENT_PAGE = "/pages/addEventPage.jsp";
    public static final String ADD_SPORT_KIND_PAGE = "/pages/addSportKind.jsp";
    public static final String ADD_MEMBER_PAGE = "/pages/addMemberPage.jsp";
    public static final String PERSONAL_PAGE = "/pages/personalPage.jsp";
    public static final String FINISHED_EVENTS_PAGE = "/pages/finishedEvents.jsp";
    public static final String TOP_UP_BALANCE_PAGE = "/pages/topUpBalance.jsp";
    public static final String ERROR_500 = "/pages/errors/error500.jsp";
    public static final String ERROR_404 = "/pages/errors/error404.jsp";

    public static final String TO_USERS_PAGE = "/main-servlet?command=to_users_page";
    public static final String TO_MAIN_PAGE = "/main-servlet?command=to_main_page";
    public static final String TO_LOGIN_PAGE = "/main-servlet?command=to_login_page";
    public static final String TO_REGISTER_PAGE = "/main-servlet?command=to_register_page";
    public static final String TO_ADMIN_PAGE = "/main-servlet?command=to_admin_page";
    public static final String TO_EVENT_PAGE = "/main-servlet?command=to_event_page";
    public static final String SHOW_EVENTS_BY_SPORT_KIND = "/main-servlet?command=show_events_by_sport_kind";
    public static final String TO_MAKE_BET_PAGE = "/main-servlet?command=to_make_bet_page";
    public static final String TO_WIN_BET_PAGE = "/main-servlet?command=to_win_bet_page";
    public static final String TO_DRAW_BET_PAGE = "/main-servlet?command=to_draw_bet_page";
    public static final String TO_EXACT_SCORE_BET_PAGE = "/main-servlet?command=to_exact_score_bet_page";
    public static final String TO_ADD_EVENT_RESULT_PAGE = "/main-servlet?command=to_add_event_result_page";
    public static final String TO_PERSONAL_PAGE = "/main-servlet?command=to_personal_page";
    public static final String TO_BOOKMAKER_PAGE = "/main-servlet?command=to_bookmaker_page";
    public static final String TO_ADD_EVENT_PAGE = "/main-servlet?command=to_add_event_page";
    public static final String TO_ADD_SPORT_KIND_PAGE = "/main-servlet?command=to_add_sport_kind_page";
    public static final String TO_ADD_MEMBER_PAGE = "/main-servlet?command=to_add_member_page";
    public static final String TO_FINISHED_EVENTS_PAGE = "/main-servlet?command=to_finished_events_page";
    public static final String TO_TOP_UP_BALANCE_PAGE = "/main-servlet?command=to_top_up_balance_page";

    private PagePath(){}
}
