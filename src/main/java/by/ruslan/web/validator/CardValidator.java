package by.ruslan.web.validator;

public class CardValidator {
    private static final String CAR_NUMBER_REGEX = "^\\d{16}$";

    public static boolean isCardNumberCorrect(String cardNumber) {
        boolean result = true;
        if(cardNumber == null || cardNumber.isBlank() || !cardNumber.matches(CAR_NUMBER_REGEX)){
            result = false;
        }
        return result;
    }
}
