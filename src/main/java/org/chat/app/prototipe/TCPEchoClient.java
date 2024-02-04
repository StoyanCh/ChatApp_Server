package org.chat.app.prototipe;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

public class TCPEchoClient {
    private static InetAddress host;
    private static int PORT = 1230;

    private static PrintWriter output;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            System.out.println("HOst ID not found");
            System.exit(1);
        }

        accessServer();
    }

    private static void accessServer(){
        Socket link = null;
        Scanner input = null;
        Scanner userEntry = null;
        try {
            link = new Socket(host,PORT);
            input = new Scanner(link.getInputStream());
            output= new PrintWriter(link.getOutputStream(), true);
            userEntry = new Scanner(System.in);
            String message, response;
            do{
                System.out.println("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("SERVER: " + response);
            }while (!message.equals("*CLOSE*"));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("Closing connection");
            input.close();
            userEntry.close();
            try {
                link.close();
            }catch (IOException e){
                System.out.println("Unable to disconnect");
                System.exit(1);
            }

        }
    }

    public void sendCreateUserCommand(String firstName, String lastName, String email, String password, boolean status) {
        String command = String.format("CREATE_USER:%s:%s:%s:%s:%s", firstName, lastName, email, password, status);
        output.println(command);
    }

    public void sendUpdateUserCommand(int userId, String firstName, String lastName, String email, String password, boolean status) {
        String command = String.format("UPDATE_USER:%d:%s:%s:%s:%s:%b", userId, firstName, lastName, email, password, status);
        output.println(command);
    }

    public void sendDeleteUserCommand(int userId) {
        String command = String.format("DELETE_USER:%d", userId);
        output.println(command);
    }

}
