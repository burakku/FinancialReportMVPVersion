package views;

import model.User;


public interface IRegisterView {
	/**
	 * get user id from user's input
	 */
	String getUserid();
	/**
	 * get user password from user's input
	 */
	String getPassword();
	/**
	 * get user name from user's input
	 */
	String getName();
	/**
	 * get user email from user's input
	 */
	String getEmail();
	/**
	 * set display result text to show any error
	 */
	void setRegisterText(String text);
	void goUserPage();
	/**
	 * addUser into model
	 */
	void addUser(User user);
	/**
	 * Find user by given param
	 * @param uid
	 * @return
	 */
	User findUser(String uid);
}
