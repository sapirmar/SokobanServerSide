package model;

import java.io.Serializable;

public class ClientDetails implements Serializable {
	private int id; 
	private String status;
	private int port;
	private String ip;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ClientDetails(int id, String status, int port, String ip) {
		
		this.id = id;
		this.status = status;
		this.port = port;
		this.ip = ip;
	}
	public ClientDetails(){
		
	}
	
}
