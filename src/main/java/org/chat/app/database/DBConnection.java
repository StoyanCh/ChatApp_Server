package org.chat.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;

public class DBConnection {
    public Connection connection(String dbName, String userName, String pass) {
        //ChatAppDB
        //String username = "postgres";
        //String password = "Kurama279";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbName, userName, pass);
            if (conn != null) {
                System.out.println("Connection check: Oki");
            } else {
                System.out.println("Connection check: Nope");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
