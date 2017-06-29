package db;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Records {//// להוסיף לxml

	//@EmbeddedId
	private RecordsKey key;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(name="Timer")
	private int timer;
	@Column(name="Steps")
	private int steps;



	public RecordsKey getKey() {
		return key;
	}
	public void setKey(RecordsKey key) {
		this.key = key;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public Records(String nickName , String levelId) {
		this.key = new RecordsKey(nickName, levelId);

	}
	public Records(String nickName , String levelId ,int timer , int steps) {
		this.key = new RecordsKey(nickName, levelId);
		this.timer = timer;
		this.steps =steps;

	}
	public Records() {
		this.key= new RecordsKey();
	}
	@Override
	public String toString() {
		return key.toString()+ steps + "," + timer;
	}


}
