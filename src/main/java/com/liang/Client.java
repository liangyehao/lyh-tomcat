package com.liang;

import com.liang.socket.TomcatServer;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        TomcatServer server = new TomcatServer();
        server.start();
    }
}
