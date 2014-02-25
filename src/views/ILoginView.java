package views;

import model.User;

public interface ILoginView {
	/**
	 * get user id from user's input
	 */
	String getUserid();
	/**
	 * get user password from user's input
	 */
	String getUserPassword();
	/**
	 * set display result text to show any error
	 */
	void setResultText(String text);
	void goUserPage();
	User findUser(String uid);
	void goAdminPage();
}
