package com;

public class TCPClientConnection {
    private int reconnectRetry;
    private TCPClientConnectionState state;

    TCPClientConnection() {
        reconnectRetry = 0;
    }

    public int getReconnectRetry() {
        return reconnectRetry;
    }

    public void setReconnectRetry(int reconnectRetry) {
        this.reconnectRetry = reconnectRetry;
    }

    public void changState(TCPClientConnectionState state) {
        this.state = state;
    }

    public int checkConnection() {
        return this.state.checkConnection();
    }

    public int connect(String serverAddress) {
        return this.state.connect(serverAddress);
    }

    public int disconnect() {
        return this.state.disconnect();
    }

    public int send(String mes) {
        return this.state.send(mes);
    }
}
