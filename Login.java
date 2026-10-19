import java.util.regex.Pattern;

/**
 * Handles user registration, validation and login for QuickChat.
 *
 * @author Your Name
 * @version 1.0
 */
public class Login {

    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    private boolean lastLoginSuccess = false;

    /**
     * Creates a Login object using the user's registration details.
     *
     * @param username the user's username
     * @param password the user's password
     * @param cellNumber the user's cell phone number
     * @param firstName the user's first name
     * @param lastName the user's last name
     */
    public Login(
            String username,
            String password,
            String cellNumber,
            String firstName,
            String lastName) {

        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks whether the username contains an underscore
     * and is no more than five characters long.
     *
     * @return true if the username is valid
     */
    public boolean checkUserName() {

        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /**
     * Checks whether the password contains at least eight characters,
     * one uppercase letter, one number and one special character.
     *
     * @return true if the password is valid
     */
    public boolean checkPasswordComplexity() {

        if (password == null) {
            return false;
        }

        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }

    /**
     * Checks whether the cell phone number follows the South African
     * international format used by this application.
     *
     * The pattern requires +27 followed by nine digits.
     * Pattern documentation:
     * https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/regex/Pattern.html
     *
     * @return true if the phone number is valid
     */
    public boolean checkCellPhoneNumber() {

        if (cellNumber == null) {
            return false;
        }

        return Pattern.matches("^\\+27\\d{9}$", cellNumber);
    }

    /**
     * Registers the user if all registration details are valid.
     *
     * @return a registration result message
     */
    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure "
                    + "that your username contains an underscore and is "
                    + "no more than five characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure "
                    + "that the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not "
                    + "contain an international code; please correct the "
                    + "number and try again.";
        }

        return "User registered successfully.";
    }

    /**
     * Checks whether the supplied username and password match
     * the registered details.
     *
     * @param inputUser the username entered at login
     * @param inputPass the password entered at login
     * @return true if the login details are correct
     */
    public boolean loginUser(String inputUser, String inputPass) {

        lastLoginSuccess =
                inputUser != null
                        && inputPass != null
                        && inputUser.equals(username)
                        && inputPass.equals(password);

        return lastLoginSuccess;
    }

    /**
     * Returns a message describing the login result.
     *
     * @return a successful or unsuccessful login message
     */
    public String returnLoginStatus() {

        if (lastLoginSuccess) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}