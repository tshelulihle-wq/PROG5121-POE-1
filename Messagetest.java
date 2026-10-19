import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Message class.
 *
 * Tests message IDs, recipient validation, message length,
 * message hashes and message actions.
 */
public class MessageTest {

    private Message message;

    /**
     * Creates a fresh message before each test.
     */
    // BEFORE //
    public void setUp() {
        message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );
    }

    // =========================
    // MESSAGE ID TESTS
    // =========================

    // TEST //
    public void testMessageIDIsTenDigits() {
        assertTrue(message.checkMessageID());

        assertEquals(10, message.getMessageID().length());

        assertTrue(
                message.getMessageID().matches("\\d{10}")
        );
    }

    // =========================
    // RECIPIENT TESTS
    // =========================

    // TEST //
    public void testRecipientCellCorrect() {
        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    // TEST //
    public void testRecipientCellIncorrect() {
        Message invalidMessage = new Message(
                "08575975889",
                "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not "
                        + "contain an international code. Please correct the "
                        + "number and try again.",
                invalidMessage.checkRecipientCell()
        );
    }

    // =========================
    // MESSAGE LENGTH TESTS
    // =========================

    // TEST //
    public void testMessageLengthCorrect() {
        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    // TEST //
    public void testMessageLengthExactly250Characters() {
        String text = "A".repeat(250);

        Message validMessage = new Message(
                "+27718693002",
                text
        );

        assertEquals(
                "Message ready to send.",
                validMessage.checkMessageLength()
        );
    }

    // TEST //
    public void testMessageExceeds250Characters() {
        String text = "A".repeat(260);

        Message longMessage = new Message(
                "+27718693002",
                text
        );

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                longMessage.checkMessageLength()
        );
    }

    // =========================
    // MESSAGE HASH TESTS
    // =========================

    // TEST //
    public void testMessageHashFormat() {
        String hash = message.createMessageHash();

        assertNotNull(hash);
        assertTrue(hash.contains(":"));
        assertTrue(hash.matches("\\d{2}:\\d+:[A-Z0-9]*"));
    }

    // TEST //
    public void testMessageHashContainsFirstAndLastWords() {
        String hash = message.createMessageHash();

        assertTrue(hash.endsWith("HITONIGHT"));
    }

    // =========================
    // SEND MESSAGE TESTS
    // =========================

    // TEST //
    public void testSendMessage() {
        String result = message.SentMessage(1);

        assertEquals(
                "Message successfully sent.",
                result
        );

        assertEquals(
                "Sent",
                message.getMessageStatus()
        );
    }

    // TEST //
    public void testDisregardMessage() {
        String result = message.SentMessage(2);

        assertEquals(
                "Press O to delete the message.",
                result
        );

        assertEquals(
                "Disregarded",
                message.getMessageStatus()
        );
    }

    // TEST //
    public void testInvalidMessageAction() {
        String result = message.SentMessage(4);

        assertEquals(
                "Invalid choice. Please select 1, 2 or 3.",
                result
        );
    }

    // =========================
    // STORE MESSAGE TEST
    // =========================

    // TEST //
    public void testStoreMessage() {
        message.createMessageHash();

        assertTrue(message.storeMessage());

        File file = new File("messages.json");

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    // =========================
    // MESSAGE TOTAL TEST
    // =========================

    // TEST //
    public void testTotalMessagesSentIncreases() {
        int before = Message.returnTotalMessagess();

        message.SentMessage(1);

        int after = Message.returnTotalMessagess();

        assertEquals(before + 1, after);
    }

    // =========================
    // MESSAGE DETAILS TEST
    // =========================

    // TEST //
    public void testMessageDetails() {
        message.createMessageHash();

        assertNotNull(message.getMessageID());
        assertNotNull(message.getRecipient());
        assertNotNull(message.getMessageText());
        assertNotNull(message.getMessageHash());
    }
}