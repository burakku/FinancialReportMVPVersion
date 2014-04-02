package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryModel implements UserModel{
	private static Map<String, User> userList = new HashMap<String, User>();
	// Initialize administrative account
/**
 * method to check if a user is contained in 
 * the userList by its userId
 * @param userID - the use's userID
 * @return true if the user is in the userList
 *         false if not
 */
	@Override
	public boolean checkUser(final String userId) {
		// TODO Auto-generated method stub
		return userList.containsKey(userId);
	} 

/**
 * method to get a user by its userID
 * @param userID - the use's userID
 * @return the matched user if found by the userID
 * 		   otherwise return NULL_USER
 */
	@Override
	public User getUserById(final String userID) {
		if(checkUser(userID)) {
			return userList.get(userID);
		} else {
			return User.NULL_USER;
		}
	}
	
	public void setUserList(final Map<String, User> userList) {
		MemoryModel.userList = userList;
	}

/**
 * method to get a copy of userList
 * @return userList - the userList
 * 
 */
	@Override
	public User[] getUserList() {
		final Collection<User> uCopy = userList.values();
		final User[] uList = new User[uCopy.size()]; // NOPMD by wen on 4/2/14 1:22 AM
		int index = 0;
		for (final User user : uCopy) { uList[index++] = user;
		}
		return uList;
	}
	
/**
 * method to add a new user to userList if its userID is not
 * in the userList
 * @param user - the user to be added
 * 
 */
	@Override
	public boolean addUser(final User user) {
		
		if(!checkUser(user.getUserID())) {
			userList.put(user.getUserID(), user);
			return true;
		}
		return false;
	}
	
/**
 * method to remove a specified user by its userID
 * from the userList 
 * @param user - the user to be removed
 * 
 */
	@Override
	public void removeUser(final User user) {
		userList.remove(user.getUserID());
	}
	
}
