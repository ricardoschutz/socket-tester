import java.util.Scanner;

public class App {
	
	private static int port = 3050;
	private static int timeout = 5000;
	public static String separator = " - ";
	public static String okMessage = "OK";
	public static String failMessage = "FAIL";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = 0;
		while (in.hasNext()) {
			count++;
			String[] line = in.nextLine().split(":");
			if (line.length > 1) {
				try {
					if (line[0].equalsIgnoreCase("port"))
						port = Integer.parseInt(line[1]);
					else if (line[0].equalsIgnoreCase("timeout"))
						timeout = Integer.parseInt(line[1]);
					else if (line[0].equalsIgnoreCase("separator"))
						separator = line[1];
					else if (line[0].equalsIgnoreCase("okmessage"))
						okMessage = line[1];
					else if (line[0].equalsIgnoreCase("failmessage"))
						failMessage = line[1];
					else
						(new Thread( new SocketTesterThread(line[0], Integer.parseInt(line[1]), timeout) ) ).start();
				} catch (NumberFormatException e) {
					System.out.println("Erro ao converter string em numero (Linha "+count+")");
				}
			} else if (line.length == 1) {
				(new Thread( new SocketTesterThread(line[0], port, timeout) ) ).start();
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
			System.out.println(host + ":" + port + separator + (s.getStatus() == SocketTest.SUCCESS ? okMessage : failMessage));
		}
		
	}

}
