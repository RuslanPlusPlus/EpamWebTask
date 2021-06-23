package by.ruslan.web.validator;

public class UserValidator {
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String USERNAME_REGEX = "^(?=.{1,40}$)[\\w][\\w -]*[\\w]";
    private static final String PASSWORD_REGEX = "\\w{4,40}";

    public static boolean isNameValid(String name){
        boolean result = true;
        if(name.isBlank() || !name.matches(USERNAME_REGEX)){
            result = false;
        }
        return result;
    }

    public static boolean isEmailValid(String email){
        boolean result = true;
        if(email.isBlank() || !email.matches(EMAIL_REGEX)){
            result = false;
        }
        return result;
    }

    public static boolean isPasswordValid(String password){
        boolean result = true;
        if(password.isBlank()){
            result = false;
        }
        return result;
    }
}
