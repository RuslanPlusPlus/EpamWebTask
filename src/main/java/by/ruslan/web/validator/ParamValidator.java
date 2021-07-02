package by.ruslan.web.validator;

public class ParamValidator {
    private static final String NAME_REGEX = "^(?=.{1,40}$)[\\w][\\w -]*[\\w]";
    private static final String MONEY_AMOUNT_REGEX = "[0-9]{1,3}\\.[0-9]{1,2}";
    private static final String SCORE_REGEX = "^\\d{1,4}$";

    public static boolean isNameValid(String name){
        boolean isValid = true;
        if (name == null || name.isBlank() || !name.matches(NAME_REGEX)){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isMoneyAmountValid(String money){
        boolean isValid = true;
        if (money == null || money.isBlank() || !money.matches(MONEY_AMOUNT_REGEX)){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isScoreValid(String score){
        boolean isValid = true;
        if (score == null || score.isBlank() || !score.matches(SCORE_REGEX)){
            isValid = false;
        }
        return isValid;
    }
}
