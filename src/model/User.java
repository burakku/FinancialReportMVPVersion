package model;

public class User {
	
	private String userID;
	private String password;
	private String name;
	// create a NULL_USER in case of trying to get user by a invalid useriD
	static public final User NULL_USER = new User("", "", "");
 
/*********************************
 * constructor
 * @param userID
 * @param password
 * @param name
 */
	public User(String userID, String password, String name) {
		this.userID = userID;
		this.password = password;
		this.name = name;
	}
	
/**************************
 * getPassword method
 * @return password - the password for the user
 */
	public String getPassword() {
		return password;
	}
	
/***********************
 * setPassword method
 * @param password - the password to be set
 */
	public void setPassword(String password) {
		this.password = password;
	}
	
/************************
 * getName method
 * @return name - the user's name
 */
	public String getName() {
		return name;
	}
	
/************************
 * setName method
 * @param name - the name to be set
 */
	public void setName(String name) {
		this.name = name;
	}

/*************************
 * getUserID method
 * @return userID
 */
	public String getUserID() {
		return userID;
	}
	
	
}
