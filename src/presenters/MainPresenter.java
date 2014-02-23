package presenters;

import views.IMainView;

public class MainPresenter {
	private IMainView view;
	
	public MainPresenter(IMainView m) {
		view = m;
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
