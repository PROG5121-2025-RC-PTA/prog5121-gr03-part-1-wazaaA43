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

import loginandsignup.MessageManager;
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
    private MessageManager messageManager = new MessageManager();


    public QuickChat() {
        initComponents(); 
        if (MessageManager.storedMessages.isEmpty()) {
            MessageManager.preloadTestMessages();
            refreshMessagesArea();
        }

        this.message = inputField.getText().trim(); 
        askUsername(); // Ask for name
        welcomeUser(); // Ask for message limit
   
        messageManager.loadMessages();
        displayMessages();
        
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

    private void displayMessages() {
        messageArea.setText("");
        for (String msg : messageManager.getAllMessages()) {
            messageArea.append(msg + "\n");
        }
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
        longestMessageButton = new javax.swing.JButton();
        searchByID = new javax.swing.JButton();
        showTotalMessages = new javax.swing.JButton();
        inputField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        viewRecents = new javax.swing.JButton();
        exitChat = new javax.swing.JButton();
        searchMessage = new javax.swing.JButton();
        deleteMessage = new javax.swing.JButton();
        searchIDField = new javax.swing.JTextField();

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

        longestMessageButton.setText("Show longest message");
        longestMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longestMessageButtonActionPerformed(evt);
            }
        });

        searchByID.setText("Search by ID");
        searchByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByIDActionPerformed(evt);
            }
        });

        showTotalMessages.setText("Show total messages sent");
        showTotalMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTotalMessagesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(longestMessageButton)
                            .addComponent(searchByID)
                            .addComponent(showTotalMessages)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(jLabel1)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showTotalMessages)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(longestMessageButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchByID)
                        .addGap(63, 63, 63))))
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

        searchIDField.setText("searchIDField: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchMessage)
                    .addComponent(deleteMessage))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(searchIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(sendButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewRecents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitChat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(viewRecents, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(exitChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteMessage)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchMessage)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
    
   public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\+27\\d{9}")) {
            JOptionPane.showMessageDialog(
                this,
                "Invalid phone number format. Must include country code.",
                "Invalid Number",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
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

        // Ask user for phone number
        recipientPhoneNumber = JOptionPane.showInputDialog(
            this, "Enter recipient's SA phone number (9 digits only, e.g. 761234567):"

        );

        if (recipientPhoneNumber == null || recipientPhoneNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone number input cancelled or empty.");
            return;
        }

        recipientPhoneNumber = "+27" + recipientPhoneNumber.trim();

        if (!isValidPhoneNumber(recipientPhoneNumber)) {
            JOptionPane.showMessageDialog(this, "Invalid South African phone number. Must be 9 digits after +27.");
            return;
        }

        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Message cannot be empty.");
            messageManager.addDisregardedMessage(message);
            return;
        } else {
            messageManager.addSentMessage(formatted); // formatted includes recipient and time
        }

        if (message.length() > 50) {
            JOptionPane.showMessageDialog(this, "Please enter a message of less than 50 characters.");
            return;
        }

        // Generate a unique message ID
        String messageID;
        int attempts = 0;
        do {
            messageID = generateMessageID();
            attempts++;
            if (attempts > 5) {
                JOptionPane.showMessageDialog(this, "Error: Unable to generate unique message ID.");
                return;
            }
        } while (!isUniqueMessageID(messageID));

        // Format message string for storage
        String timeStamp = java.time.LocalTime.now().withNano(0).toString();
        String formatted = senderName + " → " + recipientPhoneNumber + ": " + message + " (" + timeStamp + ") [ID: " + messageID + "]";

        // Present message options to the user
        int choice = JOptionPane.showOptionDialog(
            this, "Choose an option:", "Message Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, new String[]{"Send", "Discard", "Store"}, "Send"
        );

        if (choice == JOptionPane.NO_OPTION) {
            messageManager.addDisregardedMessage(message);
            JOptionPane.showMessageDialog(this, "Message discarded.");
            return;
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            messageManager.addMessage(formatted); // Stored for later
            JOptionPane.showMessageDialog(this, "Message saved for later.");
            return;
        }

        // Create and send the message
        Message msg = new Message(messageID, recipientPhoneNumber, message);
        // Add message ID and hash to their arrays
        messageManager.messageIDs.add(messageID);

        String messageHash = msg.getMessageHash(); // already created
        messageManager.messageHashes.add(messageHash);

        msg.sendMessage();

        messageArea.append(formatted + "\n");
        messageManager.addMessage(formatted);
        inputField.setText("");
        messagesSent++;

        //Refresh the messages area to reflect the newly added message
        refreshMessagesArea();
        
        String summary = String.format(
            "Message Sent Successfully!\n\n" +
            "Message ID: %s\n" +
            "Message Hash: %s\n" +
            "Recipient: %s\n" +
            "Message: %s\n" +
            "Total Messages Sent: %d",
            messageID, messageHash, recipientPhoneNumber, message, Message.getTotalMessagesSent()
        );

        JOptionPane.showMessageDialog(this, summary, "Message Info", JOptionPane.INFORMATION_MESSAGE);

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
        "Sender: " + senderName + "\n" +
        "Recipient: " + recipientPhoneNumber + "\n" +
        "Message ID: " + messageID + "\n" +
        "Message Hash: " + messageHash + "\n" +
        "Message: " + message, 
        "Message Sent", JOptionPane.INFORMATION_MESSAGE);


        storedMessages.add(formatted); // Store the message only if sending is successful
        storeMessage(formatted);
    }
 
    private void viewRecentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRecentsActionPerformed
       javax.swing.JOptionPane.showMessageDialog(this, "Coming Soon.");
       
       MessageManager.loadMessages();
       refreshMessagesArea(); //update UI
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
        
        String keyword = searchTextField.getText().trim();
        List<String> results = messageManager.searchMessages(keyword);
        messageArea.setText("Search Results:\n");
        for (String msg : results) {
            messageArea.append(msg + "\n");
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

        String idToDelete = deleteTextField.getText().trim();
        boolean success = messageManager.deleteMessageById(idToDelete);
        if (success) {
            JOptionPane.showMessageDialog(this, "Message deleted.");
            refreshMessagesArea(); //update message list
        } else {
            JOptionPane.showMessageDialog(this, "Message with ID not found.");
        }
        displayMessages();

    }//GEN-LAST:event_deleteMessageActionPerformed

    private void longestMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longestMessageButtonActionPerformed
       String longest = messageManager.getLongestMessage();

        if (longest != null && !longest.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest,
                    "Longest Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No messages have been sent yet.",
                    "Longest Message", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_longestMessageButtonActionPerformed

    private void searchByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIDActionPerformed
        String rawInput = searchIDField.getText().trim();

        if (rawInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Message ID to search.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Extract just the ID if user entered something like "[ID: 0703145798]"
        String id = rawInput.replace("[ID: ", "").replace("]", "").trim();

        String foundMessage = messageManager.searchMessageByID(id);

        if (foundMessage != null) {
            JOptionPane.showMessageDialog(null, "Message found:\n" + foundMessage,
                    "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No message found with ID: " + id,
                    "Search Result", JOptionPane.ERROR_MESSAGE);
            refreshMessagesArea(); // reset full list after failed search
        }
    }//GEN-LAST:event_searchByIDActionPerformed

    private void showTotalMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTotalMessagesActionPerformed
        int total = messageManager.getTotalMessagesSent();

        JOptionPane.showMessageDialog(null,
            "Total Messages Sent: " + total,
            "Message Count",
            JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_showTotalMessagesActionPerformed

       
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
                                            
        String rawInput = searchIDField.getText().trim();

        if (rawInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Message ID to delete.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Extract the raw ID from formats like "[ID: 0703145798]" or just "0703145798"
        String id = rawInput.replace("[ID: ", "").replace("]", "").trim();

        boolean deleted = messageManager.deleteMessageById(id);

        if (deleted) {
            JOptionPane.showMessageDialog(null, "Message with ID: " + id + " has been deleted.",
                    "Delete Successful", JOptionPane.INFORMATION_MESSAGE);
            refreshMessagesArea();  // <- Optional, if you have this method to reload the text area
        } else {
            JOptionPane.showMessageDialog(null, "No message found with ID: " + id,
                    "Delete Failed", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void refreshMessagesArea() {
        StringBuilder builder = new StringBuilder();

        for (String msg : messageManager.storedMessages) {
            builder.append(msg).append("\n\n");
        }

        messageArea.setText(builder.toString().trim());
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
    private javax.swing.JButton longestMessageButton;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JButton searchByID;
    private javax.swing.JTextField searchIDField;
    private javax.swing.JButton searchMessage;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton showTotalMessages;
    private javax.swing.JButton viewRecents;
    // End of variables declaration//GEN-END:variables
}
