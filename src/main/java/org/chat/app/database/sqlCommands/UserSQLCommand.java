package org.chat.app.database.sqlCommands;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

public class UserSQLCommand {
    private final Connection connection;

    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";
    public static final String UPDATE_USER_STATUS = "UPDATE users SET status = ? WHERE email = ? AND password = ?";

    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String GET_USER = "SELECT * FROM users WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String LOGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";

    public UserSQLCommand(Connection connection) {
        this.connection = connection;
    }

    public void createUser(String firstName, String lastName, String email, String password) {
        String sql = "INSERT INTO chatappdb_schema.User_(FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(String firstName, String lastName, String password, String email) {
        String sql = "UPDATE chatappdb_schema.User_ SET firstname = ?, lastname = ?, password = ? WHERE email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, password);
            pstmt.setString(4, email);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
