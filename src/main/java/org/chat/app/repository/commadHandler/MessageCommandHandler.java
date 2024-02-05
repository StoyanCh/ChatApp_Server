package org.chat.app.repository.commadHandler;

import org.chat.app.model.Message;
import org.chat.app.repository.MessageRepository;

public class MessageCommandHandler {
    private MessageRepository messageRepository;

    public MessageCommandHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void handleCommand(String command) {
        String[] parts = command.split(":");
        String commandType = parts[0];

        switch (commandType) {
            case "CREATE_MESSAGE":
                handleCreateMessageCommand(parts);
                break;
            case "DELETE_MESSAGE":
                handleDeleteMessageCommand(parts);
                break;
            default:
                System.out.println("Unknown command: " + commandType);
        }
    }

    private void handleCreateMessageCommand(String[] parts) {
        if (parts.length != 5) {
            System.out.println("Invalid command format. Expected: CREATE_MESSAGE:senderId:receiverId:conversationId:messageContent");
            return;
        }

        int senderId = Integer.parseInt(parts[1]);
        int receiverId = Integer.parseInt(parts[2]);
        int conversationId = Integer.parseInt(parts[3]);
        String messageContent = parts[4];

        Message message = messageRepository.createMessage(senderId, receiverId, conversationId, messageContent);

        System.out.println("Message created successfully with ID: " + message.getMessageId());
    }
    private void handleDeleteMessageCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Invalid command format. Expected: DELETE_MESSAGE:messageId");
            return;
        }
        int messageId = Integer.parseInt(parts[1]);
        messageRepository.deleteMessage(messageId);

        System.out.println("Message deleted successfully");
    }
}
