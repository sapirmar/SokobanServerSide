package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@Embeddable
public class RecordsKey implements Serializable {
	private String NickName;
	private String LevelID;


	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getLevelID() {
		return LevelID;
	}

	public void setLevelID(String levelID) {
		LevelID = levelID;
	}

	public RecordsKey(String nickName, String levelID) {
		NickName = nickName;
		LevelID = levelID;
	}

	public RecordsKey() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LevelID == null) ? 0 : LevelID.hashCode());
		result = prime * result + ((NickName == null) ? 0 : NickName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordsKey other = (RecordsKey) obj;
		if (LevelID == null) {
			if (other.LevelID != null)
				return false;
		} else if (!LevelID.equals(other.LevelID))
			return false;
		if (NickName == null) {
			if (other.NickName != null)
				return false;
		} else if (!NickName.equals(other.NickName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return NickName + "," + LevelID + ",";
	}

}
