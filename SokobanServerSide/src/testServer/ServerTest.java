package testServer;

import model.server.MyServer;
import model.server.Server;

public class ServerTest {

	public static void main(String[] args) throws Exception {
		
		Server server = new MyServer(8787);
		server.start();
		
	}

}
