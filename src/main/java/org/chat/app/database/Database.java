package org.chat.app.database;

import java.sql.*;
import java.math.*;

public class Database {
    public static void main(String[] args) {
        String jdbsURL = "jdbc:postgresql://localhost:5432/ChatAppDB";
        String username = "postgres";
        String password = "Kurama279";

        try {
            Connection connection = DriverManager.getConnection(jdbsURL, username, password);
            System.out.println("Connection to PostgreSQL server");

            String sql ="INSERT INTO chatappdb_schema.user_(firstname, lastname, email, password, status)" +
                    "VALUES ('Христо', 'Христов', 'hristomir112@gmail.com','123456789',false)";

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if(rows>0){
                System.out.println("A new user has been inserted.");
            }
            connection.close();
        }catch (SQLException e){
            System.out.println("Error in connecting to PostgreSQL server");
            e.printStackTrace();
        }
    }
}
