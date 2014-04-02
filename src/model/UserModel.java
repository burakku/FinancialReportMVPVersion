package model;

/**
 * A Facade for the actual model, which specifies 
 * what methods the Model class contains. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public interface UserModel {
	boolean checkUser(String userId);
	User getUserById(String userID);
	User[] getUserList();
	boolean addUser(User user);
	void removeUser(User user);
	
	 
}
