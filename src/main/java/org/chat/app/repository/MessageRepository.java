package org.chat.app.repository;

import org.chat.app.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Message createMessage(int senderId, int receiverId, int conversationId, String messageContent);
    void deleteMessage(int messageId);
    Optional<Message> findById(int messageId);
    List<Message> getMessagesInConversation(int conversationId);
}
