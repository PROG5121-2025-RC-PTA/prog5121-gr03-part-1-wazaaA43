package test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import loginandsignup.MessageManager;

public class MessageManagerTest {
    
     @Before
    public void setUp() {
        MessageManager.clearMessages(); // Optional, if you have it
        MessageManager.preloadTestMessages(); // Load 5 test messages
    }

    @Test
    public void testMessagesArrayPopulated() {
        assertEquals(5, MessageManager.getStoredMessages().size());
    }

    @Test
    public void testDeveloperAndSentMessages() {
        List<String> messages = MessageManager.getStoredMessages();
        boolean containsCake = messages.stream().anyMatch(m -> m.contains("Did you get the cake?"));
        boolean containsDinner = messages.stream().anyMatch(m -> m.contains("It is dinner time!"));

        assertTrue(containsCake);
        assertTrue(containsDinner);
    }

    @Test
    public void testLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = MessageManager.getLongestMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByDeveloperMessageID() {
        String result = MessageManager.searchMessageByID("1000000004");
        assertNotNull(result);
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        List<String> results = MessageManager.searchByRecipient("+27838884567");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(msg -> msg.contains("Where are you?")));
        assertTrue(results.stream().anyMatch(msg -> msg.contains("Ok, I am leaving without you.")));
    }

    @Test
    public void testDeleteByHash() {
        String id = "1000000002";
        String message = MessageManager.searchMessageByID(id);
        String hash = MessageManager.generateMessageHash(id, message);

        boolean deleted = MessageManager.deleteMessageByHash(hash);
        assertTrue(deleted);

        String result = MessageManager.searchMessageByID(id);
        assertNull(result);
    }

    @Test
    public void testMessageReportContainsDetails() {
        List<String> report = MessageManager.getStoredMessages();
        assertTrue(report.stream().allMatch(msg -> msg.contains("ID:") && msg.contains("Flag:")));
        assertTrue(report.get(0).contains("→") || report.get(0).contains("Developer"));
    }
}

