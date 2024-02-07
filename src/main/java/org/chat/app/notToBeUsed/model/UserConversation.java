package org.chat.app.notToBeUsed.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class UserConversation {
    @Id
    private int userConversationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "conversationid")
    private Conversation conversation;

    @OneToMany(mappedBy = "userConversation")
    private List<Message> messages;

}
