package model.server;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * Interface Client handler. for handle the connected clients.
 * @author Sapir Markel and Roee Sisso
 *
 */
public interface ClientHandler {
	public void handle(InputStream inFromClient, OutputStream outToClient,int idClient) throws Exception;
}
