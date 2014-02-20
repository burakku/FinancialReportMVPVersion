package model;

public class User {
	private String userID;
	private String password;
	private String name;
	
	
	public User(String userID, String password, String name) {
		this.userID = userID;
		this.password = password;
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}
	
	
}
