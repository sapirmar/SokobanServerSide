package db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import model.server.ClientHandler;

public class RecordrsHandler implements ClientHandler {
/**
 * @param idclient in this function its mean 1-search,2-sort,3-add 
 */
	private RecordsControl rc=new RecordsControl();
	
	@Override
	public void handle(InputStream inFromClient, OutputStream outToClient, int idClient) throws Exception {
		RecordsControl rc=new RecordsControl();
		BufferedReader reader=new BufferedReader(new InputStreamReader(inFromClient));
		String input=reader.readLine();
		String [] splitInput=input.split(",");
		//search
		if(idClient==1){
			//table,column,parameter
			search(splitInput[1], splitInput[2], splitInput[3]);
		}
		//sort
		else if(idClient==2){
			sort(splitInput[1], splitInput[2], Integer.parseInt(splitInput[3]));
		}
		//add
		else if(idClient==3){
			addClient(splitInput[1], splitInput[2], Integer.parseInt(splitInput[3]), Integer.parseInt(splitInput[4]));
			
		}
		
		reader.close();

	}
	
	public void addClient(String nickName,String levelId,int timer,int steps){
		rc.add_Record(nickName, levelId, timer, steps);
	}
	public void search(String table,String column,String parameter){
		rc.searchOnDb(table, column, parameter);
	}
	public void sort(String sorter,String table,int num){
		rc.sort_topDb(sorter, table, num);
	}
}
