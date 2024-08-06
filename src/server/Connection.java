package server;

import sockets.Client;
import sockets.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    public static void StartServer() {
        System.out.println("Logs from your program will appear here!");
        ServerSocket serverSocket = null;

        try {
            serverSocket = Server.start();
            System.out.println("Server started on port 4221");

            // Loop infinito para aceitar múltiplas conexões
            while (true) {
                Socket clientSocket = null;
                try {
                    clientSocket = Client.start(serverSocket);
                    String jsonResponse = jsonResponse();
                    String httpResponse = headers(jsonResponse);
                    clientSocket.getOutputStream().write(httpResponse.getBytes());
                    clientSocket.getOutputStream().flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    if (clientSocket != null) {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.out.println("IOException while closing client socket: " + e.getMessage());
                        }
                    }
                }
            }
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("IOException while closing server socket: " + e.getMessage());
                }
            }
        }
    }

    private static String jsonResponse(){
        return "{\"message\": \"Hello, Joao!\"}";
    }

    private static String headers(String jsonResponse){
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + jsonResponse.length() + "\r\n" +
                "\r\n" +
                jsonResponse;
    }

}
