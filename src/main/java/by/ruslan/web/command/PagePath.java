package by.ruslan.web.command;

public final class PagePath {
    public static final String INDEX_JSP = "/index.jsp";
    public static final String MAIN_PAGE = "/pages/main.jsp";
    //public static final String MAIN_SERVLET_PATH = "main-servlet?command=to_main_page";
    //public static final String LOGIN_SERVLET_PATH = "main-servlet?command=to_login_page";
    public static final String SIGN_IN = "/pages/signin.jsp";
    public static final String SIGN_UP = "/pages/signup.jsp";
    public static final String USER_LIST = "/pages/user-list.jsp";
    public static final String ERROR_500 = "/pages/errors/error500.jsp";
    public static final String ERROR_404 = "/pages/errors/error404.jsp";

    private PagePath(){}
}
