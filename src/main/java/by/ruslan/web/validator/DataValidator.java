package by.ruslan.web.validator;

public class DataValidator {
    public static boolean isValidInteger(String number){
        try {
            Integer.parseInt(number);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean isValidDouble(String number){
        try {
            Double.parseDouble(number);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
