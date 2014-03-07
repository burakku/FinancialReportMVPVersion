package views;

import model.Transaction;

public interface IAddTransactionView {
	String getName();
	String getType();
	String getDate();
	String getAmount();
	String getBKDisname();
	
	boolean addTrans(Transaction t);
	void setText(String t);
	void goBack();
}
