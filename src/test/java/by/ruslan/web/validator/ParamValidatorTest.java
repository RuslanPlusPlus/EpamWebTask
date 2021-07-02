package by.ruslan.web.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ParamValidatorTest {

    @DataProvider(name = "validName")
    public Object[][] createValidUsernameData(){
        Object[][] data = new Object[][]{
                {"New - York"},
                {"Real Madrid"},
                {"Barcelona"},
                {"Football"},
                {"Liverpool - Real Madrid"}
        };
        return data;
    }

    @Test(dataProvider = "validName")
    public void isNameValidPositiveTest(String name) {
        boolean result = ParamValidator.isNameValid(name);
        assertTrue(result);
    }

    @DataProvider(name = "invalidName")
    public Object[][] createInvalidUsernameData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"Football "}
        };
        return data;
    }

    @Test(dataProvider = "invalidName")
    public void isNameValidNegativeTest(String name) {
        boolean result = ParamValidator.isNameValid(name);
        assertTrue(result);
    }

    @DataProvider(name = "validMoney")
    public Object[][] createValidMoneyData(){
        Object[][] data = new Object[][]{
                {"222.12"},
                {"102.00"},
        };
        return data;
    }

    @Test(dataProvider = "validMoney")
    public void isMoneyValidPositiveTest(String money) {
        boolean result = ParamValidator.isMoneyAmountValid(money);
        assertTrue(result);
    }

    @DataProvider(name = "invalidMoney")
    public Object[][] createInvalidMoneyData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"Ewt"},
                {"22223.122"},
                {"102"},
                {"102."},
                {".12"},
                {"-12.00"}
        };
        return data;
    }

    @Test(dataProvider = "invalidMoney")
    public void isMoneyValidNegativeTest(String money) {
        boolean result = ParamValidator.isMoneyAmountValid(money);
        assertTrue(result);
    }

    @DataProvider(name = "validScore")
    public Object[][] createValidScoreData(){
        Object[][] data = new Object[][]{
                {"222"},
                {"1"},
                {"1121"},
        };
        return data;
    }

    @Test(dataProvider = "validScore")
    public void isScoreValidPositiveTest(String score) {
        boolean result = ParamValidator.isScoreValid(score);
        assertTrue(result);
    }

    @DataProvider(name = "invalidScore")
    public Object[][] createInvalidScoreData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"222233"},
                {"-23"},
                {"11.232"},
                {"waf"}
        };
        return data;
    }

    @Test(dataProvider = "invalidScore")
    public void isScoreValidNegativeTest(String score) {
        boolean result = ParamValidator.isScoreValid(score);
        assertTrue(result);
    }
}