package org.chat.app.model;
import javax.persistence.*;
import java.util.List;

@ Entity
public class UserConversation {
    @Id
    private int userConversationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private Conversation conversation;

    @OneToMany(mappedBy = "userConversation")
    private List<Message> messages;

    public int getUserConversationId() {
        return userConversationId;
    }

    public void setUserConversationId(int userConversationId) {
        this.userConversationId = userConversationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
