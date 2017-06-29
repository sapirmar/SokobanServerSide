package db;

import java.util.List;

public interface IDB {
	public void add_Record(String nickName, String levelId, int timer, int steps);
	public List<Records> sort_topDb(String sorter , String table, int num);
	public List<Records> searchOnDb(String table ,String column, String parameter);
}
