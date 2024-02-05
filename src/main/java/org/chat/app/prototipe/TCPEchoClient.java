package org.chat.app.prototipe;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoClient {
    private Socket socket;
    private PrintWriter output;

    public TCPEchoClient(String serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendCommand(String command) {
        output.println(command);
    }

    public void sendCreateUserCommand(String firstName, String lastName, String email, String password, boolean status) {
        String command = String.format("CREATE_USER:%s:%s:%s:%s:%s", firstName, lastName, email, password, status);
        sendCommand(command);
    }

    public void sendUpdateUserCommand(int userId, String firstName, String lastName, String email, String password, boolean status) {
        String command = String.format("UPDATE_USER:%d:%s:%s:%s:%s:%b", userId, firstName, lastName, email, password, status);
        sendCommand(command);
    }

    public void sendDeleteUserCommand(int userId) {
        String command = String.format("DELETE_USER:%d", userId);
        sendCommand(command);
    }

    public void sendCreateConversationCommand(String conversationName) {
        String command = String.format("CREATE_CONVERSATION:%s", conversationName);
        sendCommand(command);
    }

    public void sendDeleteConversationCommand(int conversationId) {
        String command = String.format("DELETE_CONVERSATION:%d", conversationId);
        sendCommand(command);
    }

    public void sendCreateMessageCommand(int senderId, int receiverId, int conversationId, String messageContent) {
        String command = String.format("CREATE_MESSAGE:%d:%d:%d:%s", senderId, receiverId, conversationId, messageContent);
        sendCommand(command);
    }

    public void sendDeleteMessageCommand(int messageId) {
        String command = String.format("DELETE_MESSAGE:%d", messageId);
        sendCommand(command);
    }

    public static void main(String[] args) throws IOException {
        TCPEchoClient client = new TCPEchoClient("localhost", 1230);

        client.sendCreateUserCommand("John", "Doe", "john.doe@example.com", "password123", true);
        client.sendUpdateUserCommand(1, "Jane", "Doe", "jane.doe@example.com", "password123", false);
        client.sendDeleteUserCommand(1);

        client.sendCreateConversationCommand("My Conversation");
        client.sendDeleteConversationCommand(1);

        client.sendCreateMessageCommand(1, 2, 1, "Hello, world!");
        client.sendDeleteMessageCommand(1);
    }
}
