import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class MessageTest {

    @BeforeEach
    public void setUp() {
        Message.clearAllForTesting();
        Message.currentSender = "Lihle Tshelu";
    }

    private void populateTestData() {
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        m1.createMessageHash();
        m1.SentMessage(1);

        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        m2.createMessageHash();
        m2.SentMessage(3);

        Message m3 = new Message("+27834484567", "Yohooo, I am at your gate.");
        m3.createMessageHash();
        m3.SentMessage(2);

        Message m4 = new Message("0838884567", "It is dinner time !");
        m4.createMessageHash();
        m4.SentMessage(1);

        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.");
        m5.createMessageHash();
        m5.SentMessage(3);
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        populateTestData();
        Assertions.assertEquals(2, Message.getSentMessagesArray().size());
        Assertions.assertEquals("Did you get the cake?", Message.getSentMessagesArray().get(0));
        Assertions.assertEquals("It is dinner time !", Message.getSentMessagesArray().get(1));
    }

    @Test
    public void testDisplayLongestMessage() {
        populateTestData();
        String longest = Message.getLongestMessage();
        Assertions.assertEquals("Where are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchForMessageID() {
        populateTestData();
        String result = Message.searchByMessageID("0838884567");
        boolean containsMessage = result.contains("It is dinner time !");
        Assertions.assertTrue(containsMessage, "Search should return It is dinner time !");
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        populateTestData();
        String result = Message.searchByRecipient("+27838884567");
        boolean hasFirst = result.contains("Where are you? You are late! I have asked you to be on time.");
        boolean hasSecond = result.contains("Ok, I am leaving without you.");
        Assertions.assertTrue(hasFirst, "Should contain Message 2");
        Assertions.assertTrue(hasSecond, "Should contain Message 5");
    }

    @Test
    public void testDeleteMessageUsingHash() {
        populateTestData();
        Message m2 = null;
        for (Message m : Message.getAllMessagesArray()) {
            if (m.getMessageText().equals("Where are you? You are late! I have asked you to be on time.")) {
                m2 = m;
            }
        }
        Assertions.assertNotNull(m2, "Message 2 should exist");
        String result = Message.deleteMessageByHash(m2.getMessageHash());
        Assertions.assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
        boolean stillThere = Message.getStoredMessagesArray().contains("Where are you? You are late! I have asked you to be on time.");
        Assertions.assertFalse(stillThere, "Deleted message should not be in stored array");
    }

    @Test
    public void testDisplayReport() {
        populateTestData();
        String report = Message.displayReport();
        Assertions.assertTrue(report.contains("Did you get the cake?"));
        Assertions.assertTrue(report.contains("It is dinner time !"));
        Assertions.assertTrue(report.contains("Message Hash:"));
        Assertions.assertTrue(report.contains("Recipient:"));
    }

    @Test
    public void testStoredMessagesFromJson() {
        populateTestData();
        Message.getStoredMessagesArray().clear();
        Message.loadStoredMessagesFromJson();
        boolean hasMsg2 = Message.getStoredMessagesArray().contains("Where are you? You are late! I have asked you to be on time.");
        Assertions.assertTrue(hasMsg2);
    }

    @Test
    public void testDisregardedArray() {
        populateTestData();
        boolean hasDisregarded = Message.getDisregardedMessagesArray().contains("Yohooo, I am at your gate.");
        Assertions.assertTrue(hasDisregarded);
    }
}