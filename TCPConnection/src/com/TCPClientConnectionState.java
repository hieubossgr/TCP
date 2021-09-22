/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author hieub
 */
interface TCPClientConnectionState {
    public int checkConnection();
    public int connect(String server);
    public int disconnect();
    public int send(String mes);
}
