package org.chat.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1230;
    public static void main(String[] args) {

        System.out.println("\nOpening port...");
        try{
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("\nUnable to attach port...");
            System.exit(1);
        }
        do{
            handle();

        }while (true);
    }

    private static void handle(){
        Socket link = null;
        Scanner input = null;

        try {
            link = serverSocket.accept();
            input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            int numMessages = 0;

            String message = input.nextLine();

            while (!message.equals("*CLOSE*")){
                System.out.println("\nMessage received...");
                numMessages++;
                output.println("Message "+ numMessages+" : "+ message);
                message = input.nextLine();
            }
            output.println("Messages received: " + numMessages);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("\nClose connection...");
            input.close();
            try {
                link.close();
            }catch (IOException e){
                System.out.println("\nUnable to close...");
                System.exit(1);
            }
        }
    }
}
