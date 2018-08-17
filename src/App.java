import java.util.Scanner;

public class App {
	
	private static int port = 3050;
	private static int timeout = 5000;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		while (in.hasNext()) {
			count++;
			String[] hostport = in.nextLine().split(":");
			if (hostport.length > 1) {
				try {
					if (hostport[0].equalsIgnoreCase("port"))
						port = Integer.parseInt(hostport[1]);
					else if (hostport[0].equalsIgnoreCase("timeout"))
						timeout = Integer.parseInt(hostport[1]);
					else
						(new Thread( new SocketTesterThread(hostport[0], Integer.parseInt(hostport[1]), timeout) ) ).start();
				} catch (NumberFormatException e) {
					System.out.println("Erro ao converter string em numero (Linha "+count+")");
				}
			} else if (hostport.length == 1) {
				(new Thread( new SocketTesterThread(hostport[0], port, timeout) ) ).start();
			}
		}
		in.close();
	}
	
	public static class SocketTesterThread implements Runnable {

		private SocketTest s = null;
		private String host = null;
		private int port = App.port, timeout = App.timeout;

		public SocketTesterThread(String host) {
			this(host, 0, 0);
		}
		
		public SocketTesterThread(String host, int port) {
			this(host, port, 0);
		}
		
		public SocketTesterThread(String host, int port, int timeout) {
			this.host = host;
			if (port > 0 && port < 65535) this.port = port;
			if (timeout > 0 && timeout < 60001) this.timeout = timeout;
		}

		@Override
		public void run() {
			s = new SocketTest(host, port, timeout);
			System.out.println(host + ":" + port + " - " + (s.getStatus() == SocketTest.SUCCESS ? "OK" : "FAIL"));
		}
		
	}

}
