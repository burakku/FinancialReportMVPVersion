package views;

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
	 * set display result text to show any error
	 */
	void setRegisterText(String text);
	void goUserPage();
}
