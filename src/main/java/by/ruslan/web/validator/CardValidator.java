package by.ruslan.web.validator;

/**
 * The {@code CardValidator} class is responsible for
 * card info validation
 *
 * @author Ruslan Nedvedskiy
 */
public class CardValidator {
    private static final String CAR_NUMBER_REGEX = "^\\d{16}$";

    /**
     * Check if card number format is valid
     *
     * @param cardNumber card number
     * @return boolean result
     */
    public static boolean isCardNumberCorrect(String cardNumber) {
        boolean result = true;
        if(cardNumber == null || cardNumber.isBlank() || !cardNumber.matches(CAR_NUMBER_REGEX)){
            result = false;
        }
        return result;
    }
}
