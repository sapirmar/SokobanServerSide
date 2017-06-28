package model.server;
/**
 * Server
 * @author Sapir Markel and Roee Sisso
 *
 */
public interface Server {
	/**
	 * start the server
	 * @throws Exception
	 */
	public void start()throws Exception;
	/**
	 * stop the server
	 * @throws Exception
	 */
	public void stop() throws Exception;
}
