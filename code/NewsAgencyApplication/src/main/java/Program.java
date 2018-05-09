import com.application.connection.Server;

public class Program {
	private static final String HOST = "localhost";
	private static final int PORT = 8090;
	
	public static void main(String[] args) {
		try {
			new Server(HOST, PORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
