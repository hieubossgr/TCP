package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TCPClient {
    public final static int SERVER_PORT = 8;
    public final static String SERVER_IP = "127.0.0.1";
    public static Socket socket = null;
    public static TCPClientConnection tcpClient;
    public static Scanner in = new Scanner(System.in);

    public static void checkConnection() {
        int check = tcpClient.checkConnection();
        switch (check) {
            case 1: {
                System.out.println("--------------------------------------");
                System.out.println("Đã kết nối với Server");
                tcpClient.connect(SERVER_IP);
                tcpClient.send(socket.toString());
                break;
            }
            case 2: {
                System.out.println("--------------------------------------");
                System.out.println("Đang kết nối với Server...");
                tcpClient.setReconnectRetry(tcpClient.getReconnectRetry()+1);
                if(tcpClient.getReconnectRetry() <= 5) {
                    tcpClient.changState(new ConnectedState(socket));
                    tcpClient.connect(SERVER_IP);
                }
                else {
                    tcpClient.changState(new DisconnectState());
                    tcpClient.disconnect();

                }
                break;
            }
            case 3: {
                System.out.println("--------------------------------------");
                tcpClient.disconnect();
                tcpClient.changState(new ConnectingState());
                break;
            }
        }
    }

    public static void changeServer() {
        System.out.println("------------------------------------");
        System.out.println("Chọn yêu cầu với hệ thống:");
        System.out.println("1. Đã tạo một kết nối với server (Connected)");
        System.out.println("2. Tạo một kết nối server (Connecting)");
        System.out.println("3. Hủy kết nối với server (Disconnect)");
        System.out.print("Chọn yêu cầu: ");
        int choise = Integer.parseInt(in.nextLine());
        switch (choise) {
            case 1: {
                tcpClient.changState(new ConnectedState(socket));
                break;
            }
            case 2: {
                tcpClient.changState(new ConnectingState());
                break;
            }
            case 3: {
                tcpClient.changState(new DisconnectState());
                break;
            }
            default: {
                System.out.println("Yêu cầu của bạn không hợp lệ! Vui lòng nhập lại");
                changeServer();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        tcpClient = new TCPClientConnection();
        tcpClient.changState(new DisconnectState());
        changeServer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                checkConnection();
                System.out.println("Bạn có muốn thay đổi trạng thái không?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Lựa chọn của bạn: ");
                int ch = Integer.parseInt(in.nextLine());
                System.out.println("Cập nhật thành công");
                if(ch==1) {
                    changeServer();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0,20000);
    }
}
