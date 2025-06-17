/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandsignup;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.*;
import java.security.MessageDigest;
import java.time.LocalTime;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;


public class MessageManager {

    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();

    public static void addMessage(String msg) {
        storedMessages.add(msg);
        saveMessages();
    }

    public static boolean deleteMessageById(String id) {
        Iterator<String> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            String msg = iterator.next();
            if (msg.contains("ID: " + id)) {
                iterator.remove();
                saveMessages();
                return true;
            }
        }
        return false;
    }

    public static List<String> searchMessages(String keyword) {
        List<String> results = new ArrayList<>();
        for (String msg : storedMessages) {
            if (msg.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(msg);
            }
        }
        return results;
    }

    public static List<String> getAllMessages() {
        return storedMessages;
    }

    public static void loadMessages() {
        try {
            File file = new File("storedMessages/storedMessages.json");
            if (!file.exists()) return;

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));
            storedMessages.clear();
            for (Object obj : jsonArray) {
                storedMessages.add((String) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveMessages() {
        try {
            //checks if the 'storedMessages' folder exists
            // If it doesn't, it's create it to avoid file not found errors
            File directory = new File("storedMessages");
            if (!directory.exists()) {
                directory.mkdirs(); // This makes the folder if it's not already there
            }


            try (FileWriter file = new FileWriter("storedMessages/storedMessages.json")) {
                JSONArray array = new JSONArray();
                array.addAll(storedMessages);
                file.write(array.toJSONString());
                file.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String searchMessageByID(String id) {
        for (String msg : storedMessages) {
            if (msg.contains("ID: " + id)) {
                return msg;
            }
        }
        return null;
    }

    
    public static void storeMessage(String message, String messageID, String hash) {
        sentMessages.add(message);
        messageIDs.add(messageID);
        messageHashes.add(hash);
    }

    public static void discardMessage(String message) {
        disregardedMessages.add(message);
    }

    public static void addSentMessage(String msg) {
        sentMessages.add(msg);
    }

    public static void addDisregardedMessage(String msg) {
        disregardedMessages.add(msg);
    }

    
   public static String getLongestMessage() {
        String longestMessage = "";
        int maxLength = 0;

        for (String msg : storedMessages) {
            int start = msg.indexOf(":") + 1;
            int end = msg.indexOf("(", start);
            if (start > 0 && end > start) {
                String body = msg.substring(start, end).trim();
                if (body.length() > maxLength) {
                    maxLength = body.length();
                    longestMessage = body;
                }
            }
        }

        return longestMessage;
    }


    public static String searchByID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return sentMessages.get(i);
            }
        }
        return "Message ID not found.";
    }

   public static List<String> searchByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        for (String msg : storedMessages) {
            if (msg.contains(recipient)) {
                results.add(msg);
            }
        }
        return results;
    }


    public static boolean deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                sentMessages.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                return true;
            }
        }
        return false;
    }

    public static String generateReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < sentMessages.size(); i++) {
            report.append("Message: ").append(sentMessages.get(i)).append("\n");
            report.append("ID: ").append(messageIDs.get(i)).append("\n");
            report.append("Hash: ").append(messageHashes.get(i)).append("\n\n");
        }
        return report.toString();
    }
    
    public int getTotalMessagesSent() {
        return sentMessages.size();
    }

    public static void preloadTestMessages() {
        String time = LocalTime.now().withNano(0).toString(); // Capture the current time for consistency

        String msg1 = "User → +27834557896: Did you get the cake? (" + time + ") [ID: 1000000001] [Flag: Sent]";
        String msg2 = "User → +27838884567: Where are you? You are late! I have asked you to be on time. (" + time + ") [ID: 1000000002] [Flag: Stored]";
        String msg3 = "User → +27834484567: Yohoooo, I am at your gate. (" + time + ") [ID: 1000000003] [Flag: Disregard]";
        String msg4 = "Developer: 0838884567: It is dinner time! (" + time + ") [ID: 1000000004] [Flag: Sent]";
        String msg5 = "User → +27838884567: Ok, I am leaving without you. (" + time + ") [ID: 1000000005] [Flag: Stored]";

        storedMessages.add(msg1);
        storedMessages.add(msg2);
        storedMessages.add(msg3);
        storedMessages.add(msg4);
        storedMessages.add(msg5);

        saveMessages(); // Persist to file
    }

    public static List<String> getStoredMessages() {
        return new ArrayList<>(storedMessages); // Return a copy for safety
    }

    public static void clearMessages() {
        storedMessages.clear();
        saveMessages(); // Optional: if you want to clear the file too
    }

    public static String generateMessageHash(String id, String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((id + content).getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   public static boolean deleteMessageByHash(String hash) {
        Iterator<String> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            String msg = iterator.next();
            int idStart = msg.indexOf("[ID: ") + 5;
            int idEnd = msg.indexOf("]", idStart);
            if (idStart < idEnd) {
                String id = msg.substring(idStart, idEnd);
                String genHash = generateMessageHash(id, msg);
                if (genHash.equals(hash)) {
                    iterator.remove();
                    saveMessages();
                    return true;
                }
            }
        }
        return false;
    }


    private static String extractMessageID(String msg) {
        int start = msg.indexOf("[ID: ");
        int end = msg.indexOf("]", start);
        if (start != -1 && end != -1) {
            return msg.substring(start + 5, end).trim();
        }
        return null;
    }

    
}  

