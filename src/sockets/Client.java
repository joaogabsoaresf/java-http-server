package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static Socket start(ServerSocket server) {
        Socket client;
        try {
            client = server.accept();
            if (client.isConnected()) {
                System.out.println("Accepted new connection");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
}
