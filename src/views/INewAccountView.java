package views;

import model.BankAccount;

/**
 * Interface to the view for creating new account
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public interface INewAccountView {
	/**
	 * get AccountName from user's input
	 */
	String getAcName();
	
	/**
	 * get AccountDisplayName from user's input
	 */
	String getDisName();

	/**
	 * get Userid from intent extra.
	 */
	String getUserid();
	
	/**
	 * get Balance from user's input
	 */
	String getBalance();
	
	/**
	 * get Monthly Interest Rate from user's input
	 */
	String getMIR();
	
	/**
	 * add new account into database
	 */
	void addAccount(BankAccount bankaccount);
	
	/**
	 * set display result text to show any error
	 */
	boolean checkAccount();
	/**
	 * set the text in the bar graph
	 */
	void setText(String text);
	/**
	 * redirect to account page
	 */
	void goAccountPage();

	
}
