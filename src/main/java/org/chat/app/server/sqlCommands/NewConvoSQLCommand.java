package org.chat.app.server.sqlCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewConvoSQLCommand
{
    private final Connection connection;
    public NewConvoSQLCommand(Connection connection) {
        this.connection = connection;
    }

    public void createConversation(String conversationName) {
        String sql = "INSERT INTO chatappdb_schema.Conversation (ConversationName) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, conversationName);
            pstmt.executeUpdate();
            System.out.println("Conversation created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUserConversation(long userOne, long userTwo, long conversationId) {
        String sql = "INSERT INTO chatappdb_schema.User_Conversation (UserOne, UserTwo, ConversationID) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, userOne);
            pstmt.setLong(2, userTwo);
            pstmt.setLong(3, conversationId);
            pstmt.executeUpdate();
            System.out.println("User_Conversation created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
