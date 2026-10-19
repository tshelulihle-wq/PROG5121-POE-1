/**
 * Main class for the QuickChat application.
 *
 * Users must register and log in successfully before accessing QuickChat.
 * The application allows users to send, store, or disregard messages.
 *
 * @author Your Name
 * @version 1.0
 */

import java.util.Scanner;

public class Main {

    /**
     * Runs the QuickChat application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =========================
        // CREATE ACCOUNT
        // =========================

        System.out.println("===== CREATE ACCOUNT =====");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Login user = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        // Boolean validation variables
        boolean usernameCorrect = user.checkUserName();
        boolean passwordCorrect = user.checkPasswordComplexity();
        boolean cellPhoneCorrect = user.checkCellPhoneNumber();

        // Username validation
        if (usernameCorrect) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println(
                    "Username is not correctly formatted; please ensure "
                            + "that your username contains an underscore and is "
                            + "no more than five characters in length."
            );
        }

        // Password validation
        if (passwordCorrect) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println(
                    "Password is not correctly formatted; please ensure "
                            + "that the password contains at least eight characters, "
                            + "a capital letter, a number, and a special character."
            );
        }

        // Cell phone validation
        if (cellPhoneCorrect) {
            System.out.println(
                    "Cell phone number successfully captured."
            );
        } else {
            System.out.println(
                    "Cell number is incorrectly formatted or does not "
                            + "contain an international code; please correct the "
                            + "number and try again."
            );
        }

        // Check registration
        boolean registrationSuccessful =
                usernameCorrect
                        && passwordCorrect
                        && cellPhoneCorrect;

        System.out.println(user.registerUser());

        if (!registrationSuccessful) {
            System.out.println(
                    "Registration failed. Please restart and enter valid details."
            );

            scanner.close();
            return;
        }

        // =========================
        // LOGIN
        // =========================

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccessful = user.loginUser(
                loginUsername,
                loginPassword
        );

        System.out.println(user.returnLoginStatus());

        // QuickChat can only be accessed after successful login
        if (!loginSuccessful) {
            System.out.println(
                    "You must log in successfully to use QuickChat."
            );

            scanner.close();
            return;
        }

        // =========================
        // QUICKCHAT APPLICATION
        // =========================

        System.out.println("\nWelcome to QuickChat.");

        int messageLimit = -1;

        while (messageLimit < 0) {
            System.out.print(
                    "How many messages would you like to enter? "
            );

            try {
                messageLimit = Integer.parseInt(scanner.nextLine());

                if (messageLimit < 0) {
                    System.out.println(
                            "Please enter zero or a positive whole number."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number."
                );

                messageLimit = -1;
            }
        }

        int messagesProcessed = 0;
        boolean running = true;

        // Continue until the user chooses Quit
        while (running) {

            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {

                case "1":

                    if (messagesProcessed >= messageLimit) {
                        System.out.println(
                                "You have reached the number of messages "
                                        + "you selected."
                        );
                        break;
                    }

                    System.out.print(
                            "Enter recipient cell number (+27XXXXXXXXX): "
                    );
                    String recipient = scanner.nextLine();

                    System.out.print("Enter your message: ");
                    String messageText = scanner.nextLine();

                    Message message = new Message(
                            recipient,
                            messageText
                    );

                    // Validate message ID
                    if (!message.checkMessageID()) {
                        System.out.println("Message ID is invalid.");
                        break;
                    }

                    System.out.println(
                            "Message ID generated: "
                                    + message.getMessageID()
                    );

                    // Validate recipient
                    String recipientResult =
                            message.checkRecipientCell();

                    System.out.println(recipientResult);

                    // Validate message length
                    String lengthResult =
                            message.checkMessageLength();

                    System.out.println(lengthResult);

                    if (!recipientResult.equals(
                            "Cell phone number successfully captured.")
                            || !lengthResult.equals(
                            "Message ready to send.")) {

                        System.out.println(
                                "Please correct the message details "
                                        + "before continuing."
                        );

                        break;
                    }

                    // Create message hash
                    System.out.println(
                            "Message Hash: "
                                    + message.createMessageHash()
                    );

                    // Choose what to do with the message
                    System.out.println("\nChoose an action:");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");

                    System.out.print("Enter your choice: ");

                    int action;

                    try {
                        action = Integer.parseInt(scanner.nextLine());

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Invalid choice. Please enter 1, 2 or 3."
                        );
                        break;
                    }

                    String actionResult = message.SentMessage(action);

                    System.out.println(actionResult);

                    if (action >= 1 && action <= 3) {
                        messagesProcessed++;
                    }

                    // Display details only when the message is sent
                    if (action == 1
                            && actionResult.equals(
                            "Message successfully sent.")) {

                        System.out.println("\n===== MESSAGE DETAILS =====");

                        System.out.println(
                                "Message ID: "
                                        + message.getMessageID()
                        );

                        System.out.println(
                                "Message Hash: "
                                        + message.getMessageHash()
                        );

                        System.out.println(
                                "Recipient: "
                                        + message.getRecipient()
                        );

                        System.out.println(
                                "Message: "
                                        + message.getMessageText()
                        );
                    }

                    break;

                case "2":

                    System.out.println("Coming Soon.");
                    break;

                case "3":

                    running = false;

                    System.out.println(
                            "Thank you for using QuickChat."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid option. Please select 1, 2 or 3."
                    );
            }
        }

        // =========================
        // MESSAGE SUMMARY
        // =========================

        System.out.println("\n===== MESSAGE SUMMARY =====");

        System.out.println(
                "Total messages sent: "
                        + Message.returnTotalMessagess()
        );

        System.out.println("\n===== SENT MESSAGES =====");

        System.out.println(Message.printMessages());

        scanner.close();
    }
}