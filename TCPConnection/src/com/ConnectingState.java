package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectingState implements TCPClientConnectionState{
    public final static int SERVER_PORT = 8;
    public static Socket socket = null;

    ConnectingState() {

    }

    @Override
    public int checkConnection() {
        return 2;
    }

    @Override
    public int connect(String server) {
        try {
            socket = new Socket(server, SERVER_PORT);    // Connect to server
            System.out.println("Kết nối Server thành công");
//            System.out.println("Connected " + socket);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int disconnect() {

        return 0;
    }

    @Override
    public int send(String mes) {
        System.out.println("Lỗi!!! Chưa kết nối với Server");
        return 0;
    }

    public void finalize() {

    }
}
