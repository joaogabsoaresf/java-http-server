package sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static ServerSocket start() {
        ServerSocket server;
        try {
            server = new ServerSocket(4221);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return server;
    }
}
