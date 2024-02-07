package org.chat.app.notToBeUsed.model;
import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class Message {
    @Id
    private int messageId;
    private long senderId;
    private long receiverId;
    private long conversationId;
    private java.sql.Timestamp timeStamp;
    private String message;
    private boolean statusSent;
    private boolean statusRead;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private UserConversation userConversation;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatusSent() {
        return statusSent;
    }

    public void setStatusSent(boolean statusSent) {
        this.statusSent = statusSent;
    }

    public boolean isStatusRead() {
        return statusRead;
    }

    public void setStatusRead(boolean statusRead) {
        this.statusRead = statusRead;
    }

    public User getSender() {
        return sender;
    }

    public UserConversation getUserConversation() {
        return userConversation;
    }
}
