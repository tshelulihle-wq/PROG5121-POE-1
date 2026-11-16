public static void showStoredMessagesMenu(Scanner scanner) {
    Message.loadStoredMessagesFromJson();
    boolean inMenu = true;
    while (inMenu) {
        System.out.println("\n===== STORED MESSAGES =====");
        System.out.println("a. Display sender and recipient of all stored messages");
        System.out.println("b. Display the longest stored message");
        System.out.println("c. Search for message ID and display corresponding recipient and message");
        System.out.println("d. Search for all messages stored for a particular recipient");
        System.out.println("e. Delete a message using the message hash");
        System.out.println("f. Display report that lists full details of all stored messages");
        System.out.println("g. Back to main menu");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine().toLowerCase();
        switch (choice) {
            case "a": System.out.println(Message.displaySenderAndRecipient()); break;
            case "b": System.out.println("Longest message: " + Message.getLongestMessage()); break;
            case "c":
                System.out.print("Enter Message ID: ");
                System.out.println(Message.searchByMessageID(scanner.nextLine()));
                break;
            case "d":
                System.out.print("Enter recipient (e.g. +27838884567): ");
                System.out.println(Message.searchByRecipient(scanner.nextLine()));
                break;
            case "e":
                System.out.print("Enter message hash: ");
                System.out.println(Message.deleteMessageByHash(scanner.nextLine()));
                break;
            case "f": System.out.println(Message.displayReport()); break;
            case "g": inMenu = false; break;
            default: System.out.println("Invalid option.");
        }
    }
}

void main() {
}