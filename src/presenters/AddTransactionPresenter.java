package presenters;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.MyDate;

import views.IAddTransactionView;

/**
 * Presenter to handle any logic related to users'
 * add transaction activities.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class AddTransactionPresenter {
	/**
	 * Add transaction activity view.
	 */
    private final transient IAddTransactionView view;
	
/**
 * Constructor for adding transaction presenter.
 * 
 * @param atview the view
 */
    public AddTransactionPresenter(final IAddTransactionView atview) {
        view = atview;
    }
	
/**
 * geTypeList method which returns the array list of the type 
 * of the transactions.
 * 
 * @return types the categories of the transactions
 */
    public ArrayList<String> getTypeList() { // NOPMD by hailin on 3/29/14 12:58 AM
        final List<String> types = new ArrayList<String>();
        types.add("Withdrawl");
        types.add("Deposit");
        return (ArrayList<String>) types;
    }
	
/**
 * Handle the submit button click in the UI,
 * Submit the users' option.
 * 
 */
    public void onSubmitClick() {
        String name;
        MyDate date;
        String amount;
        String type;
        String bankName;
        String userid;
        String resultText = ""; // NOPMD by hailin on 3/28/14 7:07 PM
        String category;
        name = view.getName();
        date = new MyDate(view.getDate());
        amount = view.getAmount();
        type = view.getType();
        category = view.getCategory();
        if (name.equals("") || amount.equals("") || type.equals("")) { // NOPMD by hailin on 3/29/14 12:58 AM
            resultText = "Please fill out all the fields";
        }
        else {
            bankName = view.getBKDisname();
            userid = view.getUserid();
            final double dbamount = Double.parseDouble(amount);
            if (view.addTrans(new Transaction(name, type, date, dbamount,
            		bankName, userid, category))) {
                view.goBack();
            } else {
                resultText = "Don't have enough balance.";
            }
        }
        view.setText(resultText);
    }
	
}
