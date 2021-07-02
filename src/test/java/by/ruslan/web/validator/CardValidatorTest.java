package by.ruslan.web.validator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CardValidatorTest {

    @DataProvider(name = "validCardNumber")
    public Object[][] createValidCardData(){
        Object[][] data = new Object[][]{
                {"7889110333378942"},
                {"1411078965230124"}
        };
        return data;
    }

    @DataProvider(name = "invalidCardNumber")
    public Object[][] createInvalidCardData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"141107"},
                {"141107896523012"}
        };
        return data;
    }

    @Test(dataProvider = "validCardNumber")
    public void isValidCardNumberPositiveTest(String cardNumber) {
        boolean result = CardValidator.isCardNumberCorrect(cardNumber);
        assertTrue(result);
    }

    @Test(dataProvider = "invalidCardNumber")
    public void isValidCardNumberNegativeTest(String cardNumber) {
        boolean result = CardValidator.isCardNumberCorrect(cardNumber);
        assertTrue(result);
    }

}