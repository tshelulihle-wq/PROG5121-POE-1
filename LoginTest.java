
public class LoginTest {
    // ==========================================
    // ASSERT TRUE / FALSE TESTS
    // ==========================================
    // Username correctly formatted
    // Test data: Lih_1
    @Test
    public void testUsernameCorrect() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertTrue(login.checkUserName());
    }
    // Username incorrectly formatted
    // Test data: Lihle!!!!!!!
    @Test
    public void testUsernameIncorrect() {
        Login login = new Login(
                "Lihle!!!!!!!",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertFalse(login.checkUserName());
    }
    // Password meets complexity requirements
    // Test data: Al&&ulu@th20!
    @Test
    public void testPasswordCorrect() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertTrue(login.checkPasswordComplexity());
    }
    // Password does not meet complexity requirements
    // Test data: password
    @Test
    public void testPasswordIncorrect() {
        Login login = new Login(
                "Lih_1",
                "password",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertFalse(login.checkPasswordComplexity());
    }
    // Cell phone correctly formatted
    // Test data: +27674776329
    @Test
    public void testCellPhoneCorrect() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertTrue(login.checkCellPhoneNumber());
    }
    // Cell phone incorrectly formatted
    // Test data: 0674776429
    @Test
    public void testCellPhoneIncorrect() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "0674776429",
                "Lihle",
                "Tshelu"
        );
        assertFalse(login.checkCellPhoneNumber());
    }
    // Login successful
    @Test
    public void testLoginSuccessful() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        // FIXED: Username must match the registered username
        assertTrue(
                login.loginUser(
                        "Lih_1",
                        "Al&&ulu@th20!"
                )
        );
    }
    // Login failed
    @Test
    public void testLoginFailed() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertFalse(
                login.loginUser(
                        "WrongUser",
                        "WrongPassword"
                )
        );
    }
    // ==========================================
    // ASSERT EQUALS TESTS
    // ==========================================
    // Username error message
    // Test data: lihle!!!!!!!
    @Test
    public void testUsernameErrorMessage() {
        Login login = new Login(
                "lihle!!!!!!!",
                "Al&&ulu@th20!",
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
    // Password error message
    // Test data: password
    @Test
    public void testPasswordErrorMessage() {
        Login login = new Login(
                "Lih_1",
                "password",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertEquals(
                "Password is not correctly formatted; please ensure "
                        + "that the password contains at least eight characters, "
                        + "a capital letter, a number, and a special character.",
                login.registerUser()
        );
    }
    // Cell phone success test
    // Test data: +27674776329
    @Test
    public void testCellPhoneSuccessMessage() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        assertTrue(login.checkCellPhoneNumber());
    }
    // Cell phone error message
    // Test data: 0674776329
    @Test
    public void testCellPhoneErrorMessage() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "0674776329",
                "Lihle",
                "Tshelu"
        );
        assertEquals(
                "Cell number is incorrectly formatted or does not "
                        + "contain an international code; please correct the "
                        + "number and try again.",
                login.registerUser()
        );
    }
    // Successful login message
    @Test
    public void testSuccessfulLoginMessage() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        login.loginUser(
                "Lih_1",
                "Al&&ulu@th20!"
        );
        assertEquals(
                "Welcome Lihle, Tshelu it is great to see you again.",
                login.returnLoginStatus()
        );
    }
    // Failed login message
    @Test
    public void testFailedLoginMessage() {
        Login login = new Login(
                "Lih_1",
                "Al&&ulu@th20!",
                "+27674776329",
                "Lihle",
                "Tshelu"
        );
        login.loginUser(
                "WrongUser",
                "WrongPassword"
        );
        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus()
        );
    }
}
