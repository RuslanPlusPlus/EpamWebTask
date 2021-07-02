package by.ruslan.web.validator;

public class UserValidator {
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String USERNAME_REGEX = "^(?=.{1,40}$)[a-zA-Z]+[\\w][\\w -]*[\\w]";
    private static final String PASSWORD_REGEX = "\\w{4,12}";

    public static boolean isUsernameValid(String name){
        boolean result = true;
        if(name == null || name.isBlank() || !name.matches(USERNAME_REGEX)){
            result = false;
        }
        return result;
    }

    public static boolean isEmailValid(String email){
        boolean result = true;
        if(email == null || email.isBlank() || !email.matches(EMAIL_REGEX)){
            result = false;
        }
        return result;
    }

    public static boolean isPasswordValid(String password){
        boolean result = true;
        if(password == null || password.isBlank() || !password.matches(PASSWORD_REGEX)){
            result = false;
        }
        return result;
    }
}
