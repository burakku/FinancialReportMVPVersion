package presenters;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.myDate;

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
	private final IAddTransactionView view;
	/**
	 * Constructor for adding transaction presenter
	 * 
	 * @param v the view
	 */
	public AddTransactionPresenter(IAddTransactionView v){
		view = v;
	}
	/**
	 * geTypeList method which returns the array list of the type 
	 * of the transactions
	 * 
	 * @return categories the categories of the transactions
	 */
	public ArrayList<String> getTypeList(){
		List<String> categories = new ArrayList<String>();
		categories.add("Withdrawl");
		categories.add("Deposit");
		return (ArrayList<String>) categories;
	}
	/**
	 * Handle the submit button click in the UI,
	 * Submit the users' option
	 * 
	 */
	public void onSubmitClick(){
		String name;
		myDate date;
		String amount;
		String type;
		String bankName;
		String userid;
		String resultText = "";
		name = view.getName();
		date = new myDate(view.getDate());
		amount = view.getAmount();
		type = view.getType();
		if(name.equals("") || date.equals("") ||amount.equals("")||type.equals("")){
			resultText = "Please fill out all the fields";
		}
		else{
			bankName = view.getBKDisname();
			userid = view.getUserid();
			double dbamount = Double.parseDouble(amount);
			if(view.addTrans(new Transaction(name,type,date,dbamount,bankName,userid))){
				view.goBack();
			} else {
				resultText = "Don't have enough balance.";
			}
		}
		view.setText(resultText);
	}
	
}
