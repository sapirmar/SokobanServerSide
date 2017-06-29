package db;

import java.util.LinkedList;
import java.util.List;
import db.DbManager;
import db.LevelDb;
import db.Records;
import db.User;

public class RecordsControl implements IDB {
	

	@Override
	public void add_Record(String nickName, String levelId, int timer, int steps) {
		DbManager<Records> records_manager = new DbManager<Records>();
		DbManager<User> user_manager = new DbManager<User>();

		DbManager<LevelDb> level_manager = new DbManager<LevelDb>();


		//System.out.println(levelId);
		if((records_manager.if_exist("Users", "NickName",nickName)).size()==0)
		user_manager.add(new User(nickName));
		if((records_manager.if_exist("Levels", "LevelID",levelId)).size()==0)
		level_manager.add(new LevelDb(levelId));
		records_manager.add(new Records(nickName, levelId, timer, steps));
		List<String> params = new LinkedList<String>();

	}

	@Override
	public List<Records> sort_topDb(String sorter, String table, int num) {
		DbManager<Records> records=new DbManager<Records>();
		List<Records> list=records.sort_top(sorter, table, num);
		System.out.println(sorter+table);
		return list;
	}

	@Override
	public List<Records> searchOnDb(String table, String column, String parameter) {
		DbManager<Records> records=new DbManager<Records>();
		return records.if_exist(table, column, parameter);
	}

}
