package org.chat.app.server;
public class Main {
    public static void main(String[] args) {
        // Start the server in a new thread
        new Thread(() -> {
            try {
                TCPEchoServer.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Wait for the server to start up
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the client in a new thread
        new Thread(() -> {
            try {
                TCPEchoClient.main(args);  // Променете това
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
