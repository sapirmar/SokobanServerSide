package db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name= "Levels")
public class LevelDb {

	@Id
	private String LevelID;
	@OneToMany
	@JoinColumn(name="LevelID")
	private List<Records> level_records;

	public LevelDb() {
		// TODO Auto-generated constructor stub
	}

	public String getLevelID() {
		return LevelID;
	}

	public void setLevelID(String levelID) {
		LevelID = levelID;
	}

	public LevelDb(String levelID) {
		LevelID = levelID;
	}

	@Override
	public String toString() {
		return "LevelDb [LevelID=" + LevelID + "]";
	}



}
