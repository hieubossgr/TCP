package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSingleServer {
    public final static int SERVER_PORT = 8;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting for a client...");
            while(true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);

                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();
                    int ch = 0;
                    while(true) {
                        ch = is.read();     // Read data from client
                        if(ch == -1) break;
                        else os.write(ch);
                    }
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error connection " + e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(serverSocket != null)
                serverSocket.close();
        }
    }
}
