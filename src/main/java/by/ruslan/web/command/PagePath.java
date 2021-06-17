package by.ruslan.web.command;

public final class PagePath {
    public static final String INDEX_JSP = "/index.jsp";
    public static final String MAIN_PAGE = "/pages/main.jsp";
    //public static final String LOGIN_SERVLET_PATH = "main-servlet?command=to_login_page";
    public static final String SIGN_IN = "/pages/signin.jsp";
    public static final String SIGN_UP = "/pages/signup.jsp";
    public static final String USER_LIST = "/pages/user-list.jsp";
    public static final String ADMIN_PAGE = "/pages/admin.jsp";
    public static final String BOOKMAKER_PAGE = "/pages/bookmaker.jsp";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String ERROR_500 = "/pages/errors/error500.jsp";
    public static final String ERROR_404 = "/pages/errors/error404.jsp";

    public static final String TO_USERS_PAGE = "/main-servlet?command=to_users_page";
    public static final String TO_MAIN_PAGE = "/main-servlet?command=to_main_page";
    public static final String TO_LOGIN_PAGE = "/main-servlet?command=to_login_page";
    public static final String TO_REGISTER_PAGE = "/main-servlet?command=to_register_page";
    public static final String TO_ADMIN_PAGE = "/main-servlet?command=to_admin_page";
    private PagePath(){}
}
