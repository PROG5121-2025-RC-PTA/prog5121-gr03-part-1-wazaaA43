package loginandsignup;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import java.time.LocalTime;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import loginandsignup.Message;
import java.util.UUID;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class QuickChat extends javax.swing.JFrame {

    // Declare instance variables 
    private boolean isLoggedIn = true;
    private int messageLimit;
    private int messagesSent = 0;
    private String senderName = "User";
    private String recipientPhoneNumber;
    private List<String> storedMessages = new ArrayList<>();
    String message;
    String timeStamp = java.time.LocalTime.now().withNano(0).toString();
    String messageID = generateMessageID();
    String formatted = senderName + ": " + message + " (" + timeStamp + ") [ID: " + messageID + "]";
    private JTextField searchTextField;
    private JTextField deleteTextField;

    public QuickChat() {
        initComponents(); 
        this.message = inputField.getText().trim(); 
        askUsername(); // Ask for name
        welcomeUser(); // Ask for message limit
   
        searchMessage = new javax.swing.JButton();
        deleteMessage = new javax.swing.JButton();

        searchTextField = new JTextField(20);
        deleteTextField = new JTextField(20);

        // Add text fields to the chat window
        this.add(searchTextField);
        this.add(deleteTextField);
        
        searchMessage.setBackground(new java.awt.Color(255, 255, 204));
        searchMessage.setForeground(new java.awt.Color(0, 153, 255));
        searchMessage.setText("Search Message");
        searchMessage.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMessageActionPerformed(evt);
            }
        });

        deleteMessage.setBackground(new java.awt.Color(255, 255, 204));
        deleteMessage.setForeground(new java.awt.Color(255, 0, 0));
        deleteMessage.setText("Delete Message");
        deleteMessage.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMessageActionPerformed(evt);
            }
        });
    }

    private void askUsername() {
        String input = JOptionPane.showInputDialog(this, "Enter your name:");
        if (input != null && !input.trim().isEmpty()) {
            senderName = input.trim();
        } else {
            senderName = "User"; // Fallback
            JOptionPane.showMessageDialog(this, "No name entered. Defaulting to 'User'.");
        }
    }
    
    private void welcomeUser() {
        String input = JOptionPane.showInputDialog(this, "How many messages would you like to send?");
        try {
            messageLimit = Integer.parseInt(input);
            JOptionPane.showMessageDialog(this, "You can now send up to " + messageLimit + " messages.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Defaulting to 5 messages.");
            messageLimit = 5;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        inputField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        viewRecents = new javax.swing.JButton();
        exitChat = new javax.swing.JButton();
        searchMessage = new javax.swing.JButton();
        deleteMessage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel1.setText("Welcome to QuickChat");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        messageArea.setColumns(20);
        messageArea.setRows(5);
        jScrollPane1.setViewportView(messageArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(275, 275, 275))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });

        sendButton.setBackground(new java.awt.Color(255, 255, 204));
        sendButton.setForeground(new java.awt.Color(255, 0, 153));
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        viewRecents.setBackground(new java.awt.Color(255, 255, 204));
        viewRecents.setForeground(new java.awt.Color(102, 51, 255));
        viewRecents.setText("View recents");
        viewRecents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRecentsActionPerformed(evt);
            }
        });

        exitChat.setBackground(new java.awt.Color(255, 255, 204));
        exitChat.setForeground(new java.awt.Color(255, 0, 0));
        exitChat.setText("Exit chat");
        exitChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitChatActionPerformed(evt);
            }
        });

        searchMessage.setText("Search");
        searchMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMessageActionPerformed(evt);
            }
        });

        deleteMessage.setText("Delete");
        deleteMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMessageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchMessage)
                    .addComponent(deleteMessage))
                .addGap(46, 46, 46)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewRecents))
                    .addComponent(exitChat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchMessage))
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewRecents, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed
        sendButtonActionPerformed(evt); // Triggers the same behaviour as clicking the Send button
    }//GEN-LAST:event_inputFieldActionPerformed

    public String generateMessageID() {
        return UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
    }
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+\\d{1,3}\\d{7,10}"); // Example: +27XXXXXXXXX
    }
    
    private void processUserChoice(int userChoice) {
        if (userChoice == 1) { // Send Message
            JOptionPane.showMessageDialog(null, "Message successfully sent.", "QuickChat", JOptionPane.INFORMATION_MESSAGE);
        } else if (userChoice == 2) { // Disregard Message
            JOptionPane.showMessageDialog(null, "Press 0 to delete message.", "QuickChat", JOptionPane.WARNING_MESSAGE);
        } else if (userChoice == 3) { // Store Message
            JOptionPane.showMessageDialog(null, "Message successfully stored.", "QuickChat", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
      if (!isLoggedIn) {
            JOptionPane.showMessageDialog(this, "Please login to send messages.");
            return;
        }

        if (messagesSent >= messageLimit) {
            JOptionPane.showMessageDialog(this, "You have reached your message limit.");
            return;
        }

        recipientPhoneNumber = JOptionPane.showInputDialog(this, "Enter recipient's phone number:");
        if (!isValidPhoneNumber(recipientPhoneNumber)) {
            JOptionPane.showMessageDialog(this, "Invalid phone number format. Must include country code.");
            return;
        }

        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Message cannot be empty.");
            return;
        }

        if (message.length() > 50) {
            JOptionPane.showMessageDialog(this, "Please enter a message of less than 50 characters.");
            return;
        }

        if (messagesSent == messageLimit) {
        JOptionPane.showMessageDialog(this, "You have sent all " + messageLimit + " messages.");
        }
        
        
       // Generate a UNIQUE message ID before formatting the message
        String messageID;
        do {
            messageID = generateMessageID();
        } while (!isUniqueMessageID(messageID));

        int choice = JOptionPane.showOptionDialog(
            this, "Choose an option:", "Message Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, new String[]{"Send", "Discard", "Store"}, "Send"
        );

        int attempts = 0;
        do {
            messageID = generateMessageID();
            attempts++;
            if (attempts > 5) { // Prevent infinite loops
                JOptionPane.showMessageDialog(this, "Error: Unable to generate unique message ID.");
                return;
            }
        } while (!isUniqueMessageID(messageID));

        if (choice == JOptionPane.CANCEL_OPTION) {
            storeMessage(formatted);
            JOptionPane.showMessageDialog(this, "Message saved for later.");
            return;
        } else if (choice == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Message discarded.");
            return;
        }

        // Create and send the message ONLY when the user chooses "Send Message"
        Message msg = new Message(messageID, recipientPhoneNumber, message);
        msg.sendMessage(messageID, recipientPhoneNumber, message);
        System.out.println("Total Messages Sent: " + Message.getTotalMessagesSent());

        String timeStamp = LocalTime.now().withNano(0).toString(); 
        String formatted = senderName + " → " + recipientPhoneNumber + ": " + message + " (" + timeStamp + ") [ID: " + messageID + "]";
        
        messageArea.append(formatted + "\n");
        storedMessages.add(formatted);
        storeMessage(formatted);
        inputField.setText("");
        messagesSent++;
        if (messagesSent == messageLimit) {
            JOptionPane.showMessageDialog(this, "You have sent all " + messageLimit + " messages.");
        }

    
    }//GEN-LAST:event_sendButtonActionPerformed

    
    public String generateMessageHash(String messageID, String message) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String input = messageID + message;
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

    private boolean isUniqueMessageID(String id) {
        for (String msg : storedMessages) {
            if (msg.contains("[ID: " + id + "]")) {
                return false;
            }
    }
    return true;
    }
    
    private void sendMessage(String messageID, String recipientPhoneNumber, String message) { 
        String messageHash = generateMessageHash(messageID, message);
        String formatted = senderName + " → " + recipientPhoneNumber + ": " + message + " (" + timeStamp + ") [ID: " + messageID + "] [Hash: " + messageHash + "]";

        JOptionPane.showMessageDialog(null, "Message Sent:\n" +
        "Message ID: " + messageID + "\n" +
        "Message Hash: " + messageHash + "\n" +
        "Recipient: " + recipientPhoneNumber + "\n" +
        "Message: " + message, 
        "Message Sent", JOptionPane.INFORMATION_MESSAGE);

        storedMessages.add(formatted); // Store the message only if sending is successful
        storeMessage(formatted);
    }
 
    private void viewRecentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRecentsActionPerformed
       javax.swing.JOptionPane.showMessageDialog(this, "Coming Soon.");

    }//GEN-LAST:event_viewRecentsActionPerformed

    private void exitChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitChatActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
       if (choice == JOptionPane.YES_OPTION) {
           System.exit(0);
       }

    }//GEN-LAST:event_exitChatActionPerformed

    private void searchMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMessageActionPerformed
        String searchQuery = searchTextField.getText(); // Get user input
        boolean found = false;

        for (String message : storedMessages) {
            if (message.contains(searchQuery)) {
                found = true;
                JOptionPane.showMessageDialog(this, "Message Found: " + message);
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Message not found.");
        }
    }//GEN-LAST:event_searchMessageActionPerformed

    private void deleteMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMessageActionPerformed
         String deleteQuery = deleteTextField.getText(); // Get user input
        boolean removed = storedMessages.removeIf(message -> message.contains(deleteQuery));

        if (removed) {
            JOptionPane.showMessageDialog(this, "Message deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Message not found.");
        }

    }//GEN-LAST:event_deleteMessageActionPerformed

       
    private String findMessageByID(String id) {
        for (String msg : storedMessages) {
            if (msg.contains("[ID: " + id + "]")) {
                return msg;
            }
        }
        return "Message not found.";
    }
    
    private boolean deleteMessageByID(String id) {
        Iterator<String> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            String msg = iterator.next();
            if (msg.contains("[ID: " + id + "]")) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    private void searchMessageByIDActionPerformed(java.awt.event.ActionEvent evt) {
                                           
        String searchID = JOptionPane.showInputDialog(this, "Enter message ID to search:");
        if (searchID != null && !searchID.trim().isEmpty()) {
            String result = findMessageByID(searchID);
            JOptionPane.showMessageDialog(this, result);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID entered.");
        }
    }
    
    private void deleteMessageByIDActionPerformed(java.awt.event.ActionEvent evt) { 
                                            
        String deleteID = JOptionPane.showInputDialog(this, "Enter message ID to delete:");
        if (deleteID != null && !deleteID.trim().isEmpty()) {
            boolean deleted = deleteMessageByID(deleteID);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Message deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Message ID not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID entered.");
        }
    }
    
    public String printMessages() {
    return storedMessages.toString();
    }
    
    public int returnTotalMessages() {
    return messagesSent;
    }
    
    private void storeMessage(String message) {
    JSONArray messagesArray = new JSONArray();
    
    try (FileReader reader = new FileReader("stored_messages.json")) {
        Object obj = new org.json.simple.parser.JSONParser().parse(reader);
        if (obj instanceof JSONArray jSONArray) {
            messagesArray = jSONArray;
        }
    } catch (Exception e) {
        // If the file doesn't exist, just initialize an empty array
    }
    
    JSONObject messageJson = new JSONObject();
    messageJson.put("message", message);
    messageJson.put("hash", generateMessageHash(messageID, message));

    messagesArray.add(messageJson);

    try (FileWriter file = new FileWriter("stored_messages.json")) {
        file.write(messagesArray.toJSONString());
        file.flush();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving message.");
    }
    }

    public String extractIDFromMessage(String message) {
        int startIndex = message.indexOf("[ID: ") + 5;
        int endIndex = message.indexOf("]", startIndex);
        return (startIndex > 4 && endIndex > startIndex) ? message.substring(startIndex, endIndex) : null;
    }
    
    private JSONArray loadStoredMessages() {
    JSONArray messagesArray = new JSONArray();

    try (FileReader reader = new FileReader("stored_messages.json")) {
        Object obj = new JSONParser().parse(reader);
        if (obj instanceof JSONArray) {
            messagesArray = (JSONArray) obj;
        }
    } catch (IOException | ParseException e) {
        System.out.println("Error loading stored messages: " + e.getMessage());
    }

    return messagesArray;
    }
    
    public String exportMessagesToJSON() {
        JSONArray messagesArray = new JSONArray();

        for (String message : storedMessages) {
            messagesArray.add(message);
        }

        try (FileWriter file = new FileWriter("stored_messages.json")) {
            file.write(messagesArray.toJSONString()); // ✅ Write messages to JSON file
            file.flush();
        } catch (IOException e) {
            System.out.println("Error exporting messages: " + e.getMessage());
        }

        return messagesArray.toJSONString(); // ✅ Returns JSON format of stored messages
    }

    public List<String> getStoredMessages() {
        return storedMessages;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new QuickChat().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteMessage;
    private javax.swing.JButton exitChat;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JButton searchMessage;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton viewRecents;
    // End of variables declaration//GEN-END:variables
}
