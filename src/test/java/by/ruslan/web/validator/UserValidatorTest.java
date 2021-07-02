package by.ruslan.web.validator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorTest {

    @DataProvider(name = "validEmail")
    public Object[][] createValidEmailData(){
        Object[][] data = new Object[][]{
                {"rus@gmail.com"},
                {"r@mail.ru"},
                {"r2001@yandex.ru"},
                {"r2001@tut.by"}
        };
        return data;
    }

    @Test(dataProvider = "validEmail")
    public void isEmailValidPositiveTest(String email) {
        boolean result = UserValidator.isEmailValid(email);
        assertTrue(result);
    }

    @DataProvider(name = "invalidEmail")
    public Object[][] createInvalidEmailData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"@gmail.com"},
                {"r@mailru"},
                {"r2001@.ru"},
                {"r2001@mail.ruuuuuw"},
        };
        return data;
    }

    @Test(dataProvider = "invalidEmail")
    public void isEmailValidNegativeTest(String email) {
        boolean result = UserValidator.isEmailValid(email);
        assertTrue(result);
    }

    @DataProvider(name = "validPassword")
    public Object[][] createValidPasswordData(){
        Object[][] data = new Object[][]{
                {"1111"},
                {"R57f"},
                {"rurTwYwr2001"},
                {"rurTwYwrOOp"},
        };
        return data;
    }

    @Test(dataProvider = "validPassword")
    public void isPasswordValidPositiveTest(String pass) {
        boolean result = UserValidator.isPasswordValid(pass);
        assertTrue(result);
    }

    @DataProvider(name = "invalidPassword")
    public Object[][] createInvalidPasswordData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"wfi  wt 9"},
                {"$$}]]2424T"},
                {"2744oiWTWTA22425watwtwatwat"},
        };
        return data;
    }

    @Test(dataProvider = "invalidPassword")
    public void isPasswordValidNegativeTest(String pass) {
        boolean result = UserValidator.isPasswordValid(pass);
        assertTrue(result);
    }

    @DataProvider(name = "validUsername")
    public Object[][] createValidUsernameData(){
        Object[][] data = new Object[][]{
                {"Ruslan"},
                {"Ruslan0528"},
                {"miki - mouse"}
        };
        return data;
    }

    @Test(dataProvider = "validUsername")
    public void isUsernameValidPositiveTest(String username) {
        boolean result = UserValidator.isUsernameValid(username);
        assertTrue(result);
    }

    @DataProvider(name = "invalidUsername")
    public Object[][] createInvalidUsernameData(){
        Object[][] data = new Object[][]{
                {null},
                {""},
                {" "},
                {"0528"},
                {"miki "},
                {"miki -"}
        };
        return data;
    }

    @Test(dataProvider = "invalidUsername")
    public void isUsernameValidNegativeTest(String username) {
        boolean result = UserValidator.isUsernameValid(username);
        assertTrue(result);
    }
}