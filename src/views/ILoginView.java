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
	/**
	 * redirect to user page
	 */
	void goUserPage();
	/**
	 * search a user to check if he or she exists
	 */
	User findUser(String uid);
	/**
	 * redirect to main page
	 */
	void goAdminPage();
	void goForgotPasswordPage();
}
