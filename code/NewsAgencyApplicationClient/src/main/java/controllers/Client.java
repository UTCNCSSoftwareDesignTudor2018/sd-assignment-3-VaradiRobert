package controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private BufferedReader in;
    private PrintWriter out;
    
    public BufferedReader getIn() {
    	return in;
    }
    
    public PrintWriter getOut() {
    	return out;
    }

    public Client() throws IOException {
    	connectToServer();
    }

    private static final int PORT = 8090;
    private static final String HOST = "localhost";
    
    public void connectToServer() throws IOException {
        @SuppressWarnings("resource")
		Socket socket = new Socket(HOST, PORT);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.connectToServer();
    }
}
