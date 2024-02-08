package org.chat.app.server.sqlCommands;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

public class UserSQLCommand {
    private final Connection connection;

    /*
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    public static final String GET_USER = "SELECT * FROM users WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String LOGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";
     */

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
        String sql = "UPDATE chatappdb_schema.User_ SET Email = ? WHERE FirstName = ? AND LastName = ? AND Password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, password);
            pstmt.setString(1, email);

            pstmt.executeUpdate();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpdateUserStatus(boolean status, String email, String password){
        String sql = "UPDATE chatappdb_schema.User_ SET status = ? WHERE email = ? AND password =?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setBoolean(1, status);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            pstmt.executeUpdate();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean userLogIn(String email, String password) {
        String sql = "SELECT * FROM chatappdb_schema.User_ WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("User is logged");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
