package cn.baidu.com.MyServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FrameServer {
	private ServerSocket ss;
	private Socket s;

	public static void main(String[] args) throws IOException {
			new FrameServer().start();
	}

	public void start() throws IOException {
		System.out.println("我正在监听端口1314");
		ss = new ServerSocket(1314);
		System.out.println("一个客户端连接到了服务器(我)    IP地址为:" + InetAddress.getLocalHost().getHostAddress());
		s = ss.accept();
		System.out.println("有一个客户端连接");
		Client client = new Client(s);
		new Thread(client).start();
		
	}

	class Client implements Runnable {

		private Socket s;
		private InputStream in;

		public Client(Socket s) {
			this.s = s;
			try {
				in = s.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) != -1) {
					System.out.println(new String(b,0,i));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}