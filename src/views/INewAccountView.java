package views;

import model.BankAccount;

/**
 * Interface to the view for creating new account.
 *
 * @version 1.0
 *
 * @author Team 23
 */
public interface INewAccountView {
/**
 * get AccountName from user's input.
 * @return name account name
 */
String getAcName();

/**
 * get AccountDisplayName from user's input.
 * @return name display name
 */
String getDisName();

/**
 * get Userid from intent extra.
 * @return userid  userid
 */
String getUserid();

/**
 * get Balance from user's input.
 * @return balance balance
 */
String getBalance();

/**
 * get Monthly Interest Rate from user's input.
 * @return mir mir
 */
String getMIR();

/**
 * add new account into database.
 * @param bankaccount bank account
 */
void addAccount(BankAccount bankaccount);

/**
 * set display result text to show any error.
 * @return checkAccount if account exists
 */
boolean checkAccount();
/**
 * set the text in the bar graph.
 * @param text text
 */
void setText(String text);
/**
 * redirect to account page.
 */
void goAccountPage();


}
