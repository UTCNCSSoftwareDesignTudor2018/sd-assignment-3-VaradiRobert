import java.io.IOException;
import java.net.ServerSocket;

public class Program {
	private static final int PORT = 8090;
	
	public static void main(String[] args) {
		
		try {
			ServerSocket socket = new ServerSocket(PORT);
			while(true) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
