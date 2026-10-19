import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a QuickChat message and manages message actions.
 *
 * @author Your Name
 * @version 1.0
 */
public class Message {

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;
    private String messageStatus;

    private static int messageCounter = 0;
    private static int totalMessagesSent = 0;

    private static final List<Message> messages = new ArrayList<>();

    /**
     * Creates a message with a generated ID and message number.
     *
     * @param recipient the recipient's cell phone number
     * @param messageText the text of the message
     */
    public Message(String recipient, String messageText) {

        this.recipient = recipient;
        this.messageText = messageText;

        this.messageNumber = messageCounter++;

        this.messageID = generateMessageID();

        this.messageStatus = "Pending";

        messages.add(this);
    }

    /**
     * Generates a random ten-digit message ID.
     *
     * @return a ten-digit message ID
     */
    private String generateMessageID() {

        Random random = new Random();

        long number = 1_000_000_000L
                + (long) (random.nextDouble() * 9_000_000_000L);

        return String.valueOf(number);
    }

    /**
     * Checks that the message ID contains ten digits.
     *
     * @return true if the message ID is valid
     */
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("\\d{10}");
    }

    /**
     * Checks whether the recipient's phone number is valid.
     *
     * @return a message describing the validation result
     */
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the "
                + "number and try again.";
    }

    /**
     * Checks whether the message is no longer than 250 characters.
     *
     * @return a message describing the message length result
     */
    public String checkMessageLength() {

        if (messageText == null) {
            return "Message exceeds 250 characters by 0; please reduce the size.";
        }

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int excessCharacters = messageText.length() - 250;

        return "Message exceeds 250 characters by "
                + excessCharacters
                + "; please reduce the size.";
    }

    /**
     * Creates a hash using the first two digits of the message ID,
     * the message number, and the first and last words of the message.
     *
     * @return the message hash
     */
    public String createMessageHash() {

        String firstTwoDigits = messageID.substring(0, 2);

        String firstWord = "";
        String lastWord = "";

        if (messageText != null && !messageText.trim().isEmpty()) {

            String[] words = messageText.trim().split("\\s+");

            firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
            lastWord = words[words.length - 1]
                    .replaceAll("[^a-zA-Z0-9]", "");
        }

        messageHash = firstTwoDigits
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;

        messageHash = messageHash.toUpperCase();

        return messageHash;
    }

    /**
     * Processes the user's choice for the message.
     *
     * Choice 1 sends the message.
     * Choice 2 disregards the message.
     * Choice 3 stores the message.
     *
     * @param choice the action selected by the user
     * @return the result of the selected action
     */
    public String SentMessage(int choice) {

        switch (choice) {

            case 1:

                messageStatus = "Sent";
                totalMessagesSent++;

                return "Message successfully sent.";

            case 2:

                messageStatus = "Disregarded";
                messages.remove(this);

                return "Press O to delete the message.";

            case 3:

                messageStatus = "Stored";

                if (storeMessage()) {
                    return "Message successfully stored.";
                }

                return "Message could not be stored.";

            default:

                return "Invalid choice. Please select 1, 2 or 3.";
        }
    }

    /**
     * Stores the message in a JSON file.
     *
     * @return true if the message was stored successfully
     */
    public boolean storeMessage() {

        try (FileWriter writer =
                     new FileWriter("messages.json", true)) {

            String jsonMessage =
                    "{"
                            + "\"messageID\":\"" + escapeJson(messageID) + "\","
                            + "\"messageNumber\":" + messageNumber + ","
                            + "\"messageHash\":\"" + escapeJson(messageHash) + "\","
                            + "\"recipient\":\"" + escapeJson(recipient) + "\","
                            + "\"message\":\"" + escapeJson(messageText) + "\","
                            + "\"status\":\"" + escapeJson(messageStatus) + "\""
                            + "},"
                            + System.lineSeparator();

            writer.write(jsonMessage);

            return true;

        } catch (IOException e) {

            System.out.println(
                    "Error storing message: " + e.getMessage()
            );

            return false;
        }
    }

    /**
     * Escapes special characters for JSON string values.
     *
     * @param value the text to escape
     * @return escaped text
     */
    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Returns the details of messages that were successfully sent.
     *
     * @return a string containing sent message details
     */
    public static String printMessages() {

        StringBuilder output = new StringBuilder();

        for (Message message : messages) {

            if ("Sent".equals(message.messageStatus)) {

                output.append("Message ID: ")
                        .append(message.messageID)
                        .append(System.lineSeparator());

                output.append("Message Hash: ")
                        .append(message.messageHash)
                        .append(System.lineSeparator());

                output.append("Recipient: ")
                        .append(message.recipient)
                        .append(System.lineSeparator());

                output.append("Message: ")
                        .append(message.messageText)
                        .append(System.lineSeparator());

                output.append("Status: ")
                        .append(message.messageStatus)
                        .append(System.lineSeparator());

                output.append(System.lineSeparator());
            }
        }

        if (output.length() == 0) {
            return "No sent messages available.";
        }

        return output.toString();
    }

    /**
     * Returns the total number of successfully sent messages.
     *
     * @return total messages sent
     */
    public static int returnTotalMessagess() {

        return totalMessagesSent;
    }

    /**
     * Returns the message ID.
     *
     * @return message ID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Returns the message number.
     *
     * @return message number
     */
    public int getMessageNumber() {
        return messageNumber;
    }

    /**
     * Returns the recipient's cell phone number.
     *
     * @return recipient number
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Returns the message text.
     *
     * @return message text
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Returns the message hash.
     *
     * @return message hash
     */
    public String getMessageHash() {
        return messageHash;
    }

    /**
     * Returns the current message status.
     *
     * @return message status
     */
    public String getMessageStatus() {
        return messageStatus;
    }
}