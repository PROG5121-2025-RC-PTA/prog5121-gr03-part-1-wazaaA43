/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandsignup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author user
 */

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private static int totalMessagesSent = 0;

    // Constructor
    public Message(String messageID, String recipient, String messageText) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
    }

    // Check if Message ID is within 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    //Validate recipient phone number format
    public boolean checkRecipientCell() {
        return recipient.matches("\\+\\d{1,3}\\d{7,10}");
    }

    // Generate SHA-256 Hash for message
    public String createMessageHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = messageID + recipient + messageText;
            byte[] hashBytes = digest.digest(input.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error generating hash.";
        }
    }

    // Send Message & Display full details
    public void sendMessage(String messageID1, String recipientPhoneNumber, String message) {
        if (!checkMessageID() || !checkRecipientCell()) {
            System.out.println("Invalid Message ID or Recipient Number.");
            return;
        }

        totalMessagesSent++; // Accumulate total messages sent
        System.out.println("Message Sent Successfully!");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + createMessageHash());
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }

    // Get the total number of messages sent
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }


}
