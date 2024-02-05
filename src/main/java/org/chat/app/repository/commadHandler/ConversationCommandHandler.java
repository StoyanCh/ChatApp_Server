package org.chat.app.repository.commadHandler;

import org.chat.app.model.Conversation;
import org.chat.app.repository.ConversationRepository;

public class ConversationCommandHandler {
    private final ConversationRepository conversationRepository;

    public ConversationCommandHandler(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public void handleCommand(String command) {
        String[] parts = command.split(":");
        String commandType = parts[0];

        switch (commandType) {
            case "CREATE_CONVERSATION":
                handleCreateConversationCommand(parts);
                break;
            case "DELETE_CONVERSATION":
                handleDeleteConversationCommand(parts);
                break;
            // Добавете други команди тук...
            default:
                System.out.println("Unknown command: " + commandType);
        }
    }

    private void handleCreateConversationCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Invalid command format. Expected: CREATE_CONVERSATION:conversationName");
            return;
        }

        String conversationName = parts[1];
        Conversation conversation = conversationRepository.createConversation(conversationName);

        System.out.println("Conversation created successfully with ID: " + conversation.getConversationId());
    }

    private void handleDeleteConversationCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Invalid command format. Expected: DELETE_CONVERSATION:conversationId");
            return;
        }

        int conversationId = Integer.parseInt(parts[1]);
        conversationRepository.deleteConversation(conversationId);

        System.out.println("Conversation deleted successfully");
    }
}

