package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryModel implements UserModel{
	private static Map<String, User> userList = new HashMap<String, User>();

	// Initialize administrative account
	static {
		userList.put("admin",new User("admin","pass1234", "Admin"));
	}
	@Override
	public boolean checkUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
