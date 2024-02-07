package org.chat.app.notToBeUsed.repository;

import org.chat.app.notToBeUsed.model.Conversation;
import org.chat.app.notToBeUsed.model.User;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository {
    // Създаване на нова беседа
    Conversation createConversation(String conversationName);

    // Изтриване на съществуваща беседа
    void deleteConversation(int conversationId);

    // Намиране на беседа по ID
    Optional<Conversation> findById(int conversationId);

    // Получаване на всички беседи
    List<Conversation> findAll();

    // Добавяне на потребител към беседа
    void addUserToConversation(int userId, int conversationId);

    // Премахване на потребител от беседа
    void removeUserFromConversation(int userId, int conversationId);

    // Получаване на всички потребители в дадена беседа
    List<User> getUsersInConversation(int conversationId);
}
