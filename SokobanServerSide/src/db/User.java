package db;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="Users")
public class User {
@Id
private String NickName;

@OneToMany
@JoinColumn(name="NickName")
private List<Records> user_records;

public User() {
	// TODO Auto-generated constructor stub
}

public String getNickName() {
	return NickName;
}

public void setNickName(String nickName) {
	NickName = nickName;
}

public User(String nickName) {
	NickName = nickName;
}

@Override
public String toString() {
	return "User [NickName=" + NickName + "]";
}

public List<Records> getUser_records() {
	return user_records;
}

public void setUser_records(List<Records> user_records) {
	this.user_records = user_records;
}





}
