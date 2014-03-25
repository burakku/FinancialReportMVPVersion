package views;

import model.Transaction;

/**
 * Interface to the view for adding transaction
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public interface IAddTransactionView {
	/**
	 * get the name of the user
	 */
	String getName();
	/**
	 * get the type of the transaction
	 */
	String getType();
	/**
	 * get the date of the transaction
	 */
	String getDate();
	/**
	 * get the amount of the transaction
	 */
	String getAmount();
	/**
	 * get the bank displaying name of the user
	 */
	String getBKDisname();
	/**
	 * get the userid of the user
	 */
	String getUserid();
	/**
	 * add transaction to the database
	 */
	boolean addTrans(Transaction t);
	/**
	 * set text displaying in the bar 
	 */
	void setText(String t);
	/**
	 * redirect to previous page
	 */
	void goBack();
}
