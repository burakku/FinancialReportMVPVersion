package presenters;

import model.BankAccount;
import views.INewAccountView;

/**
 * Presenter to handle any logic related to users'
 * creating new account activities.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class NewAccountPresenter {
	private transient final INewAccountView view;
	
	public NewAccountPresenter(final INewAccountView aview){
		view = aview;
	}
	
	/**
	 * Handle the submit button click in the UI,
	 * Check if the all fields are filled out
	 * if they are then add the new account into database
	 * then redirect back to account page
	 */
	public void onSubmitClick(){
		final String acname = view.getAcName();
		final String disname = view.getDisName();
		final String uid = view.getUserid(); // NOPMD by hailin on 3/29/14 1:05 AM
		final String balance = view.getBalance();
		final String mir = view.getMIR();
		String text = ""; // NOPMD by hailin on 3/29/14 1:05 AM
		if(acname.equals("") || disname.equals("") || balance.equals("") || mir.equals("")){ // NOPMD by hailin on 3/29/14 1:04 AM
			text = "Please fill out the names";
		} else if(view.checkAccount()){
			text = "This display name already exsit, please try another one!";
		} else {
			final double dbbalance = Double.parseDouble(balance);
			final double dbmir = Double.parseDouble(mir);
			view.addAccount(new BankAccount(acname,disname,dbbalance,dbmir,uid));
			view.goAccountPage();
		}
		view.setText(text);
		
	}
}
