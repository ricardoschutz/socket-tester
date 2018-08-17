import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTest {
	
	public static final int SUCCESS = 0;
	public static final int FAIL = 1;
	public static final int PENDING = 2;
	
	private int status = PENDING;

	public SocketTest(String host, int port, int timeout) {
		Socket s = new Socket();
			try {
				s.connect(new InetSocketAddress(host, port), timeout);
				if (!s.isClosed())
					status = SUCCESS;
				s.close();
			} catch (IOException e) {
				status = FAIL;
			}
	}
	
	public int getStatus() {
		return status;
	}
}
