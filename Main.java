//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String firstName;
        String lastName;
        String username;
        String password;
        String cellPhoneNumber;

        boolean usernameCorrect;
        boolean passwordCorrect;
        boolean cellPhoneCorrect;
        boolean loginSuccessful;

        // ==================
        // CREATE ACCOUNT
        // ==================

        System.out.println("==== CREATE ACCOUNT ====");

        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        username = scanner.nextLine();

        System.out.print("Enter password: ");
        password = scanner.nextLine();

        System.out.print("Enter cell phone number: ");
        cellPhoneNumber = scanner.nextLine();

        Login user = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        // Check username
        usernameCorrect = user.checkUserName();

        if (usernameCorrect) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println(
                    "Username is not correctly formatted; please ensure " +
                            "that your username contains an underscore and is " +
                            "no more than five characters in length."
            );
        }

        // Check password
        passwordCorrect = user.checkPasswordComplexity();

        if (passwordCorrect) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println(
                    "Password is not correctly formatted; please ensure " +
                            "that the password contains at least eight characters, " +
                            "a capital letter, a number, and a special character."
            );
        }

        // Check cell phone
        cellPhoneCorrect = user.checkCellPhoneNumber();

        if (cellPhoneCorrect) {
            System.out.println("Cell phone number successfully captured.");
        } else {
            System.out.println(
                    "Cell number is incorrectly formatted or does not " +
                            "contain an international code; please correct the " +
                            "number and try again."
            );
        }

        // Registration message
        System.out.println(user.registerUser());

        // ============
        // LOGIN
        // ============

        System.out.println("\n==== LOGIN ====");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        loginSuccessful = user.loginUser(
                loginUsername,
                loginPassword
        );

        System.out.println(user.returnLoginStatus());

        scanner.close();
    }
}

