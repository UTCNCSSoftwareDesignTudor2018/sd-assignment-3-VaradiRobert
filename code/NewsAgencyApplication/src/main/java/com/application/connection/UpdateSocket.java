package com.application.connection;

import java.net.ServerSocket;

public class UpdateSocket {
	public UpdateSocket() throws Exception {
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(8091);
        try {
            while (true) {
                new UpdateThread(listener.accept(), clientNumber++);
            }
        } finally {
            listener.close();
        }
    }
}
