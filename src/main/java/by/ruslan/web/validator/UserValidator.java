package by.ruslan.web.validator;

public class UserValidator {
    private static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    //add name, password regex

    public static boolean isNameValid(String name){
        boolean result = true;
        if(name.isBlank()){
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
