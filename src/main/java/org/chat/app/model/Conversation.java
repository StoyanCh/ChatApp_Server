package org.chat.app.model;
import javax.persistence.*;
import java.util.List;
@Entity
public class Conversation {
    @Id
    private int conversationId;
    private int userConversationId;
    private long userId;

    @OneToMany(mappedBy = "conversation")
    private List<UserConversation> userConversations;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getUserConversationId() {
        return userConversationId;
    }

    public void setUserConversationId(int userConversationId) {
        this.userConversationId = userConversationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<UserConversation> getUserConversations() {
        return userConversations;
    }

    public void setUserConversations(List<UserConversation> userConversations) {
        this.userConversations = userConversations;
    }
}
