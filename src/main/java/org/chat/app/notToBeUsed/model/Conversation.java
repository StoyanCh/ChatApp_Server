package org.chat.app.notToBeUsed.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Conversation {
    @Id
    private int conversationId;
    private String conversationName;

    @OneToMany(mappedBy = "conversation")
    private List<UserConversation> userConversations;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public List<UserConversation> getUserConversations() {
        return userConversations;
    }

    public void setUserConversations(List<UserConversation> userConversations) {
        this.userConversations = userConversations;
    }
}
