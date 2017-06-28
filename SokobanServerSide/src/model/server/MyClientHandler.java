package model.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.LinkedList;
import java.util.List;

import java.util.Observable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import model.ClientDetails;
import model.data.Level2D;
import model.data.Position;
import model.items.Actor;
import model.items.Box;
import model.items.Destination_Box;
import model.items.Items;
import model.items.Space;
import model.items.Wall;
import search.Action;
import solver.SokobanSolver;
/**
 * The protocol between the client to the server
 * @author Sapir Markel and Roee Sisso
 *
 */
public class MyClientHandler extends Observable implements ClientHandler {
	private ClientDetails clientInfo;

	public MyClientHandler(ClientDetails clientInfo) {
		this.clientInfo = clientInfo;
	}

	public MyClientHandler() {
	}
/**
 * handle the client 
 * @param input steam
 * @param output stream 
 */
	@Override
	public void handle(InputStream inFromClient, OutputStream outToClient,int idClient) throws Exception {
		
		//new client is connected
		setChanged();
		notifyObservers();
		// read the level from client
		ObjectInputStream objectReader = new ObjectInputStream(inFromClient);
		PrintWriter writer = new PrintWriter(outToClient, true);
		Level2D level = (Level2D) objectReader.readObject();
		String levelNameDb = convertTheLevelToString(level);/// row,col,@#oA
		String answerFromDb = checkFromDBSolution(levelNameDb);
		System.out.println("answerrrrrrrrrrrrrrrrrrrrrrrrrrrr from Db "+answerFromDb);
		// if there is solution from the db ,we convert him to actions and send
		// to the client
		if (answerFromDb.length()>0) {
			List<String> actionsFromDb = convertSolutionFromWebService(answerFromDb);
			
			for (String actionDb : actionsFromDb) {
				System.out.println(actionDb);
				writer.println(actionDb);
			}
		}
		//if there is no solution from DB
		else{
		SokobanSolver solver = new SokobanSolver();
		List<Action> actionsSolve = solver.solve(level).getActions();
		LinkedList<String> solutionMoves=new LinkedList<>();
		for (Action act : actionsSolve) {
			//maybe problem hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
			solutionMoves.add(act.getName());
		}
		String solutionDb=convertTheSolutionToDb(solutionMoves);
		updateSolutionToDb(levelNameDb, solutionDb);
		/// sending the commands to the client
		if (actionsSolve != null) {
			for (int i = actionsSolve.size() - 1; i >= 0; i--) {
				
				writer.println(actionsSolve.get(i).getName());
			}
		}
		}
		objectReader.close();
		writer.close();
		/// notify the server(model) that the client finished
		setChanged();
		notifyObservers();

	}
/**
 * convert to row,column,@#A
 * @param level
 * @return converted string
 */
	
	public String convertTheLevelToString(Level2D level) {
		StringBuilder sb = new StringBuilder();
		sb.append(level.getColumn());
		sb.append(level.getRow());
		for (int i = 0; i < level.getColumn(); i++) {
			for (int j = 0; j < level.getRow(); j++) {
				/// maybe we need to compress more
				sb.append(level.getWarehouse()[i][j].getFirstChar());
			}

		}
		return sb.toString();
	}
/**
 * check if there solution from the database
 * @param levelName
 * @return the solution (string)/or null if there is not solution
 */
	public String checkFromDBSolution(String levelName) {
		Client client = ClientBuilder.newClient();
		WebTarget webtarget = client.target("http://localhost:8080/SokobanServices/webapi/resources/get/" + levelName);
		Invocation.Builder builder = webtarget.request();
		
		String solution = null;
		solution = builder.get().readEntity(String.class);
		if (solution == null) {
			return null;
		}
		
		return solution;

	}
	/**
	 * update the solution to the database
	 * @param levelName (the situation from the level) 
	 * @param solution
	 */
	public void updateSolutionToDb(String levelName,String solution){
		Client client = ClientBuilder.newClient();
		System.out.println("asking for -http://localhost:8080/SokobanServices/webapi/resources/add/" + levelName+"/"+solution);
		WebTarget webtarget = client.target("http://localhost:8080/SokobanServices/webapi/resources/add/" + levelName+"/"+solution);
		Invocation.Builder builder = webtarget.request();
		Response response=builder.get();
		if(response.getStatus()!=200){
			System.out.println("bad request ,can not update the data base");
		}
	}
/**
 * convert the solution as a string to the data base
 * @param allActions
 * @return
 */
	public String convertTheSolutionToDb(List<String> allActions) {
		StringBuilder sb = new StringBuilder();
		for (String action : allActions) {
			if (action.contains("move up")) {
				sb.append("u");
			} else if (action.contains("move down")) {
				sb.append("d");
			} else if (action.contains("move right")) {
				sb.append("r");
			} else if (action.contains("move left")) {
				sb.append("l");
			}
		}
		return sb.reverse().toString();

	}

/**
 *  get convert the solution from db from u to move up ,d to move down
 * @param solution
 * @return linked list of moves
 */

	public LinkedList<String> convertSolutionFromWebService(String solution) {
		
		LinkedList<String> allActions = new LinkedList<>();
		System.out.println("solution "+solution +"solution length" +solution.length());
		for (int i = 0; i < solution.length(); i++) {
			
			switch (solution.charAt(i)) {
			case 'u': {
				allActions.add("move up");
				break;
			}
			case 'd': {
				allActions.add("move down");
				break;
			}
			case 'r': {
				allActions.add("move right");
				break;
			}
			case 'l': {
				allActions.add("move left");
				break;
			}
			default: {
				System.out.println("we got the wrong input from webservice");
				break;
			}
			}
		}
		return allActions;
	}

}
