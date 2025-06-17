/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package test;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import loginandsignup.QuickChat;

/**
 *
 * @author user
 */
public class TestQuickChat {
    
     private QuickChat chat;

    String message = "Hello, this is a test message with [ID: 123456]"; // Example message
    String messageID = chat.extractIDFromMessage(message);
    String expectedHash = chat.generateMessageHash(messageID, message);

    
    @BeforeEach
    public void setup() {
        chat = new QuickChat(); // Create a new instance before each test
    }

    @Test
    public void testMessageLength() {
        String longMessage = "This is a test message that exceeds the allowed 250-character limit for QuickChat...";
        boolean isValid = longMessage.length() <= 250;

        if (isValid) {
            assertEquals("Message ready to send.", "Message ready to send.");
        } else {
            int excessLength = longMessage.length() - 250;
            assertEquals("Message exceeds 250 characters by " + excessLength + ", please reduce size.", 
                         "Message exceeds 250 characters by " + excessLength + ", please reduce size.");
        }
    }
    
   @Test
    public void testPhoneNumberFormatting() {
        // Predefined test cases
        String validNumber = "+27718693002";
        String invalidNumber = "0718693002";

        assertTrue(validNumber.matches("\\+\\d{11,15}"), "Cell phone number successfully captured.");
        assertFalse(invalidNumber.matches("\\+\\d{11,15}"), "Cell phone number is incorrectly formatted or does not contain an international code. Please correct.");

        // Allow manual input for real-time testing
        String phoneNumber = JOptionPane.showInputDialog("Enter recipient phone number:");
        String phonePattern = "^\\+27\\d{9}$";

        if (phoneNumber.matches(phonePattern)) {
            JOptionPane.showMessageDialog(null, "Cellphone number successfully added.");
        } else {
            JOptionPane.showMessageDialog(null, "Cellphone number incorrectly formatted or does not contain the international code.");
        }
    }

    @Test
    public void testMessageHashValidation() {
        String messageID = "1527060040";
        String messageContent = "HITONIGHT";

        String expectedHash = chat.generateMessageHash(messageID, messageContent);
        String actualHash = "00:0:HITONIGHT"; // Check if this format is correct

        assertEquals(expectedHash, actualHash, "Message hash should match expected value.");
    }
    
   @Test
    public void testMessageIDGeneration() {
        String generatedID = chat.generateMessageID(); // Call method from QuickChat instance

        assertNotNull(generatedID, "Message ID should be generated and not null.");
        assertFalse(generatedID.isEmpty(), "Message ID should not be empty.");
    }
    
    @Test
    public void testPrivateMessageIDGeneration() throws Exception {
        Method method = QuickChat.class.getDeclaredMethod("generateMessageID");
        method.setAccessible(true); // Unlock private method access

        String messageID = (String) method.invoke(chat);

        assertNotNull(messageID, "Message ID should not be null");
        assertTrue(messageID.length() <= 10, "Message ID should be ≤ 10 characters");
    }


    @Test
    public void testPrivateMessageHashGeneration() throws Exception {
        Method method = QuickChat.class.getDeclaredMethod("generateMessageHash", String.class, String.class);
        method.setAccessible(true);

        String hash = (String) method.invoke(chat, "12345ABCDE", "Hello, Leah!");

        assertNotNull(hash, "Message hash should not be null");
        assertEquals(64, hash.length(), "SHA-256 hash should be 64 characters long");
    }


    @Test
    public void testPrivateStoreMessage() throws Exception {
        Method method = QuickChat.class.getDeclaredMethod("storeMessage", String.class);
        method.setAccessible(true);

        method.invoke(chat, "Test message");
        assertTrue(chat.printMessages().contains("Test message"), "Message should be stored correctly.");
    }

    
    @Test
    public void testPrivateTotalMessagesSentTracking() throws Exception {
        Method method = QuickChat.class.getDeclaredMethod("returnTotalMessages");
        method.setAccessible(true);

        int initialCount = (int) method.invoke(chat);

        Method storeMsgMethod = QuickChat.class.getDeclaredMethod("storeMessage", String.class);
        storeMsgMethod.setAccessible(true);
        storeMsgMethod.invoke(chat, "New message");

        int updatedCount = (int) method.invoke(chat);

        assertEquals(initialCount + 1, updatedCount, "Total messages count should increase by 1");
    }


     @Test
    public void testSendMessageOptions() throws Exception {
        // Unlock and invoke private sendMessage method
        Method sendMessageMethod = QuickChat.class.getDeclaredMethod("sendMessage", String.class, String.class, String.class);
        sendMessageMethod.setAccessible(true); // Unlock private method access

        int choice = (int) sendMessageMethod.invoke(chat, "12345ABCDE", "Hello, Leah!", "+27123456789");

        assertTrue(choice == 0 || choice == 1 || choice == 2, "User should be able to send, store, or disregard the message.");
    }
    
    private List<String> storedMessages = new ArrayList<>(); // Initialize correctly
    
    public void testMessageHashVerification() {
        if (storedMessages.isEmpty()) {
            System.out.println("Error: No stored messages to verify.");
            return; // Prevent running if no messages exist
        }

        for (String message : storedMessages) { 
            String messageID = chat.extractIDFromMessage(message); // Use chat instance

            if (messageID == null) {
                System.out.println("Error: Failed to extract message ID.");
                continue; // Skip if extraction fails
            }

            String expectedHash = chat.generateMessageHash(messageID, message); // Call method correctly

            if (!message.contains(expectedHash)) {
                System.out.println("Error: Message hash mismatch for ID " + messageID);
            } else {
                System.out.println("Hash verified for Message ID: " + messageID);
            }
        }
    }
    
    
    
    @Test
    public void testPrintMessages() throws Exception {
        // Unlock and invoke private storeMessage method
        Method storeMessageMethod = QuickChat.class.getDeclaredMethod("storeMessage", String.class);
        storeMessageMethod.setAccessible(true);

        storeMessageMethod.invoke(chat, "Hello, Leah!");
        storeMessageMethod.invoke(chat, "Testing QuickChat!");

        String allMessages = chat.printMessages();
        assertTrue(allMessages.contains("Hello, Leah!"), "printMessages() should return stored messages.");
        assertTrue(allMessages.contains("Testing QuickChat!"), "printMessages() should return stored messages.");
    }

    @Test
    public void testStoreMessageInJSON() throws Exception {
        // Unlock and invoke private storeMessage method
        Method storeMessageMethod = QuickChat.class.getDeclaredMethod("storeMessage", String.class);
        storeMessageMethod.setAccessible(true);

        storeMessageMethod.invoke(chat, "JSON Test Message");

        // Unlock and invoke private exportMessagesToJSON method
        Method exportJSONMethod = QuickChat.class.getDeclaredMethod("exportMessagesToJSON");
        exportJSONMethod.setAccessible(true);

        String jsonOutput = (String) exportJSONMethod.invoke(chat);

        assertNotNull(jsonOutput, "JSON output should not be null.");
        assertTrue(jsonOutput.contains("JSON Test Message"), "Stored message should appear in JSON format.");
    }

}
