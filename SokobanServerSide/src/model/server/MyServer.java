package model.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.ClientDetails;
import model.Model;

public class MyServer extends Observable implements Server, Model, Observer {
	public static final int PORT = 8787;
	public static final String SERVERIP = "127.0.0.1";
	private Thread serverThread = null;
	private int port = 0;
	private volatile boolean isStopped = false;
	private ServerSocket mainSocket = null;
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	private ArrayList<ClientDetails> allClientsList;// all clients that
													// connected to the server
	private int clientsCounter = 0;
	private MyServer thisServer = this;

	public MyServer(int port) {
		this.port = port;
		allClientsList = new ArrayList<ClientDetails>();
	}
/**
 * start the server
 */
	@Override
	public void start() throws Exception {
		serverThread = new Thread(() -> runServer());
		serverThread.start();
		

	}
/**
 * stop the server
 */
	@Override
	public void stop() throws Exception {
		isStopped = true;
		mainSocket.close();
		executor.shutdown();
		System.out.println("Closed server");

	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();

	}
/**
 * remove client from the list
 * @param client id (int)
 */
	@Override
	public ClientDetails removeClient(int ID) {
		for(int i=0;i<allClientsList.size();i++){
			if(allClientsList.get(i).getId()==ID){
				//remove the client from the list
				ClientDetails client=allClientsList.remove(i);
				//notify the observers (for view the new list)
				setChanged();
				notifyObservers();
				return client;
			}
		}
		return null;

	}
/**
 * add client to list
 * @param socket of the new client
 */
	@Override
	public ClientDetails addAclient(Socket clientSocket) {
		//client counter is the id 
		ClientDetails client=new ClientDetails(clientsCounter++,"online",clientSocket.getPort(),clientSocket.getInetAddress().toString().substring(1));
		allClientsList.add(client);
		setChanged();
		notifyObservers();
		return client;
		
	}
/**
 * get client list
 */
	@Override
	public List<ClientDetails> getClientsList() {
		return allClientsList;
	}

	private void runServer() {
		try {
			mainSocket = new ServerSocket(PORT);
			System.out.println("Server is alive and waiting for connections.");

			while (!isStopped) {
				Socket clientSocket = mainSocket.accept();
				ClientDetails client=addAclient(clientSocket);
				
				executor.submit(new Runnable() {

					@Override
					public void run() {
					
						MyClientHandler handler = new MyClientHandler();
						handler.addObserver(thisServer);
						try {
							handler.handle(clientSocket.getInputStream(), clientSocket.getOutputStream(),client.getId());
							
							/////////////////maybe we need to close the client in the client handler!!!!!!!
							/////////////////can be problem
							Thread.sleep(500);
							client.setStatus("Offline");
							setChanged();
							notifyObservers();
							Thread.sleep(1000*5);
							removeClient(client.getId());
							
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}

			mainSocket.close();

		} catch (Exception e) {
		}

	}

}
