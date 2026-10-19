import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // TEST //
    public void testUsernameCorrectlyFormatted() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertTrue(login.checkUserName());
    }

    // TEST //
    public void testUsernameIncorrectlyFormatted() {

        Login login = new Login(
                "Lihle",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkUserName());
    }

    // TEST //
    public void testUsernameTooLong() {

        Login login = new Login(
                "Lih_le",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkUserName());
    }

    // TEST //
    public void testPasswordMeetsComplexityRequirements() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    // TEST //
    public void testPasswordTooShort() {

        Login login = new Login(
                "Lih_1",
                "Pass1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // TEST //
    public void testPasswordWithoutUppercaseLetter() {

        Login login = new Login(
                "Lih_1",
                "password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // TEST //
    public void testPasswordWithoutNumber() {

        Login login = new Login(
                "Lih_1",
                "Password!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // TEST //
    public void testPasswordWithoutSpecialCharacter() {

        Login login = new Login(
                "Lih_1",
                "Password1",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // TEST //
    public void testCellPhoneNumberCorrectlyFormatted() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    // TEST //
    public void testCellPhoneNumberIncorrectlyFormatted() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "0764776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.checkCellPhoneNumber());
    }

    // TEST //
    public void testSuccessfulRegistration() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertEquals(
                "User registered successfully.",
                login.registerUser()
        );
    }

    // TEST //
    public void testRegistrationFailsWithInvalidUsername() {

        Login login = new Login(
                "Lihle",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertEquals(
                "Username is not correctly formatted; please ensure "
                        + "that your username contains an underscore and is "
                        + "no more than five characters in length.",
                login.registerUser()
        );
    }

    // TEST //
    public void testSuccessfulLogin() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertTrue(login.loginUser("Lih_1", "Password1!"));

        assertEquals(
                "Welcome Lihle, Tshelu it is great to see you again.",
                login.returnLoginStatus()
        );
    }

    @Test
    public void testUnsuccessfulLogin() {

        Login login = new Login(
                "Lih_1",
                "Password1!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );

        assertFalse(login.loginUser("WrongUser", "WrongPassword"));

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus()
        );
    }
}