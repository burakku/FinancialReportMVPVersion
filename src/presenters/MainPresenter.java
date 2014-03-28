package presenters;

import views.IMainView;

/**
 * Main Presenter to handle any logic related to users'
 * register and login activities by click in.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class MainPresenter {
	private transient final IMainView view;
	/**
	 * Constructor for main presenter
	 * 
	 * @param m the view
	 */
	public MainPresenter(final IMainView mview) {
		view = mview;
	}
	
	/**
	 * Handle the register button click in the UI,
	 * Go to register page
	 * 
	 */
	public void onRegisterClick() {
		view.goRegister();
	}
	
	/**
	 * Handle the login button click in the UI,
	 * Go to Login page
	 */
	public void onLoginClick() {
		view.goLogin();
	}
}
