package org.chat.app.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;

public class DBConnection {
    public Connection connection(String dbName, String userName, String pass, String schemaName) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, userName, pass);
            if (conn != null) {
                System.out.println("Connection check: Oki");
                // Задаване на схемата
                conn.setSchema(schemaName);
            } else {
                System.out.println("Connection check: Nope");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}