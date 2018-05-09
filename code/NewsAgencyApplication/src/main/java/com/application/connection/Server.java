package com.application.connection;

import java.net.ServerSocket;

public class Server {
    public Server(String host, int port) throws Exception {
        System.out.println("The capitalization server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(port);
        try {
            while (true) {
                new ClientThread(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }
}
