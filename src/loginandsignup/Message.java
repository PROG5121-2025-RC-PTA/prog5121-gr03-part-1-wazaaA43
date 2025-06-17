package loginandsignup;

public class Message {
    private String messageID;
    private String recipient;
    private String content;
    private static int totalMessagesSent = 0;

    // Constructor
    public Message(String messageID, String recipient, String content) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.content = content;
    }

    // ✅ Check if Message ID is 10 characters or less
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // ✅ Validate recipient cell number format: e.g., +27769877777
    public boolean checkRecipientCell() {
        return recipient.matches("\\+\\d{10,13}");
    }

    // ✅ Generate custom message hash based on assignment spec
    public String getMessageHash() {
        String firstTwoID = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        String[] words = content.trim().split("\\s+");
        int wordCount = words.length;

        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";

        return (firstTwoID + ":" + wordCount + ":" + firstWord + lastWord).toUpperCase();
    }

    // ✅ Simulate sending a message
    public void sendMessage() {
        if (!checkMessageID()) {
            System.out.println("Message ID must be 10 characters or less.");
            return;
        }

        if (!checkRecipientCell()) {
            System.out.println("Invalid recipient phone number format.");
            return;
        }

        totalMessagesSent++;

        System.out.println("Message Sent Successfully!");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + getMessageHash());
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + content);
        System.out.println("Total Messages Sent: " + totalMessagesSent);
    }

    // Getter
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    // Getters for use elsewhere if needed
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }
}
