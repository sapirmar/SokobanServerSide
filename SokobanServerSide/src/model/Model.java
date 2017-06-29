package model;

import java.net.Socket;
import java.util.List;
/**
 * Model interface.
 * @author Sapir Markel and Roee Sisso
 *
 */
public interface Model {

	public void start() throws Exception;
	
	public void stop() throws Exception;
	public ClientDetails removeClient(int ID);
	public List<ClientDetails> getClientsList();
	public ClientDetails addAclient(Socket clientSocket);
}
