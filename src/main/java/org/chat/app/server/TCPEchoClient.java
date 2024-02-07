package org.chat.app.server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPEchoClient {
    private Socket socket;
    private PrintWriter output;

    public TCPEchoClient(String serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendCommand(String command) {
        output.println(command);
    }


    public static void main(String[] args) throws IOException {

    }
}
