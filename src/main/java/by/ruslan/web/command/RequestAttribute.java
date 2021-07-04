package by.ruslan.web.command;

/**
 * The {@code RequestAttribute} class contains all request attributes.
 *
 * @author Ruslan Nedvedskiy
 */

public final class RequestAttribute {

    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String LOGIN_ERROR = "login_error";
    public static final String USERS = "users";
    public static final String EVENTS = "events";
    public static final String MEMBERS = "members";
    public static final String EVENT = "event";
    public static final String EVENT_ID = "eventId";
    public static final String SPORT_KINDS = "sportKinds";
    public static final String ERROR_NO_USERS = "errorNoUsers";
    public static final String ERROR_EMAIL_EXISTS = "errorEmailExists";
    public static final String ERROR_INCORRECT_DATA = "errorIncorrectData";
    public static final String ERROR_UPDATE_USER = "errorUpdateUser";
    public static final String NO_FINISHED_EVENTS = "noFinishedEvents";
    public static final String ERROR_TOP_UP_BALANCE = "errorTopUpBalance";
    public static final String INPUT_INCORRECT_FORMAT = "inputIncorrectFormat";

    private RequestAttribute(){}
}
