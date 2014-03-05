package views;

import model.Transaction;

public interface IAddTransactionView {
	String getName();
	String getType();
	String getDate();
	String getAmount();
	String getBKDisname();
	
	void addTrans(Transaction t);
	void setText(String t);
	void goBack();
}
