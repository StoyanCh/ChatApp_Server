package org.chat.app.server;
import org.chat.app.server.database.DBConnection;
import org.chat.app.server.sqlCommands.UserSQLCommand;
import java.sql.Timestamp;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Scanner;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1230;
    private static UserSQLCommand userSQLCommand; // Make userSQLCommand a static variable
public static void main(String[] args) {
    DBConnection connect = new DBConnection();
    Connection conn = connect.connection("ChatAppDB","postgres","Kurama279","chatappdb_schema");
    if(conn != null) {
    System.out.println("\nOpening port...");

        try {
        serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
        System.out.println("\nUnable to attach port...");
        System.exit(1);
            }
            userSQLCommand = new UserSQLCommand(conn);
        //userSQLCommand.createUser("firstName", "lastName", "email", "password");

        do {
                handle();
            }while (true);
        }
    }
    private static void handle() {
        try (
                Socket link = serverSocket.accept();
                Scanner input = new Scanner(link.getInputStream());
                PrintWriter output = new PrintWriter(link.getOutputStream(), true);
        ) {
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
        }
    }

    private static void processMessage(String message, int numMessages, PrintWriter output) {
        System.out.println("\nMessage received...");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        if (message.startsWith("createUser:")) {
            createUser(message);
        }

        output.println("Message " + numMessages + " : " + message);
        System.out.println("Message " + message);
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
