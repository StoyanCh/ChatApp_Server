package org.chat.app.server;

import org.chat.app.server.database.DBConnection;
import org.chat.app.server.sqlCommands.UserSQLCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1230;
    private static UserSQLCommand userSQLCommand; // Make userSQLCommand a static variable
    private static Connection connection;

    public static void main(String[] args) {
        DBConnection connect = new DBConnection();
        connection = connect.connection("ChatAppDB","postgres","Kurama279","chatappdb_schema");
        if(connection != null) {
            System.out.println("\nOpening port...");

            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e) {
                System.out.println("\nUnable to attach port...");
                System.exit(1);
            }
            userSQLCommand = new UserSQLCommand(connection);

            do {
                handle();
            } while (true);
        }
    }

    private static void handle() {
        Socket link = null;
        try {
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            int numMessages = 0;
            String message = "";

            while (input.hasNextLine() && !(message = input.nextLine()).equals("*CLOSE*")) {
                processMessage(message, numMessages, output);
                numMessages++;
            }

            output.println("Messages received: " + numMessages);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("\nClose connection...");
            if (link != null) {
                try {
                    link.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processMessage(String message, int numMessages, PrintWriter output) {
        System.out.println("\nMessage received...");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        if (message.startsWith("createUser:")) {
            createUser(message);
        }
        if (message.startsWith("userLogIn:")) {
            userLogIn(message);
        }

        output.println("Message " + numMessages + " : " + message);
        System.out.println("Message " + message);
    }

    private static void userLogIn(String message) {

        String[] parts = message.split(":")[1].split(",");
        String email = parts[0];
        String password = parts[1];

        boolean loginSuccessful = userSQLCommand.userLogIn(email, password);
        if (loginSuccessful) {
            // Потребителят е влязъл успешно, актуализирайте статуса му
            String statusUser = "status:true:" + email + "," + password;
            UpdateUserStatus(statusUser);
        }
    }
    private static void UpdateUserStatus(String message) {
        String[] parts = message.split(":")[1].split(",");
        boolean status = Boolean.parseBoolean(parts[0]);
        String email = parts[1];
        String password = parts[2];

        userSQLCommand.UpdateUserStatus(status, email, password);
    }

    private static void createUser(String message) {
        String[] parts = message.split(":")[1].split(",");
        String firstName = parts[0];
        String lastName = parts[1];
        String email = parts[2];
        String password = parts[3];

        userSQLCommand.createUser(firstName, lastName, email, password);
    }

}
