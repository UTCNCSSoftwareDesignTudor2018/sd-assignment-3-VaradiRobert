import com.application.connection.Server;
import com.application.connection.UpdateSocket;

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
		/**new Thread() {
			@Override
			public void run() {
				try {
					new Server(HOST, PORT);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					new UpdateSocket();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();*/
	}
}
