package views;

import model.Transaction;

/**
 * Interface to the view for adding transaction.
 *
 * @version 1.0
 *
 * @author Team 23
 */
public interface IAddTransactionView {
/**
 * get the name of the user.
 * @return name transaction name
 */
    String getName();
/**
 * get the type of the transaction.
 * @return transaction type of transaction
 */
    String getType();
/**
 * get the date of the transaction.
 * @return date date of the transaction
 */
    String getDate();
/**
 * get the amount of the transaction.
 * @return amount of the transaction
 */
    String getAmount();
/**
 * get the bank displaying name of the user.
 * @return name displayed bank account name
 */
    String getBKDisname();
/**
 * get the userid of the user.
 * @return userid user id
 */
    String getUserid();
/**
 * add transaction to the database.
 * @param transaction transaction
 * @return addtrans if transaction is added
 */
    boolean addTrans(Transaction transaction);
/**
 * set text displaying in the bar.
 * @param text text
 */
    void setText(String text);
/**
 * redirect to previous page.
 */
    void goBack();
}
