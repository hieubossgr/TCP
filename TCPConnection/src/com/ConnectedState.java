/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 *
 * @author hieub
 */
public class ConnectedState implements TCPClientConnectionState{
    public final static int SERVER_PORT = 8;
    public static Socket socket;

    ConnectedState(Socket socket) {
        this.socket = socket;
    }

    @Override
    public int checkConnection() {
        return 1;
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
        try {
            if (socket != null) {
                socket.close();
                System.out.println("Đã đóng kết nối");
            } else
                System.out.println("Chưa kết nối với Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int send(String mes) {
        System.out.println("Message: " + mes);
        return 0;
    }

    public void finalize() {

    }
}
