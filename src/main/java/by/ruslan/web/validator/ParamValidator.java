package by.ruslan.web.validator;

public class ParamValidator {
    private static final String NAME_REGEX = "^(?=.{1,40}$)[\\w][\\w -]*[\\w]";
    private static final String MONEY_AMOUNT_REGEX = "[0-9]{1,3}\\.[0-9]{1,2}";
    private static final String SCORE_REGEX = "^\\d{1,4}$";

    public static boolean isNameValid(String name){
        boolean isValid = true;
        if (name.isBlank() || !name.matches(NAME_REGEX)){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isMoneyAmountValid(String name){
        boolean isValid = true;
        if (name.isBlank() || !name.matches(MONEY_AMOUNT_REGEX)){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isScoreValid(String name){
        boolean isValid = true;
        if (name.isBlank() || !name.matches(SCORE_REGEX)){
            isValid = false;
        }
        return isValid;
    }
}
