package presenters;

import views.ISettingView;

/**
 * Presenter to handle any logic related to users'
 * account setting activities.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class SettingPresenter {
	private final ISettingView setview;
	/**
	 * Constructor for setting presenter
	 * 
	 * @param v the view
	 */
	public SettingPresenter(ISettingView v) {
		setview = v;
	}
	/**
	 * Handle the register button click in the UI,
	 * Go to account
	 */
	public void onAccountClick() {
		setview.goToAccount();
	}
	/**
	 * Handle the register button click in the UI,
	 * Logout
	 */
	public void onLogoutClick() {
		setview.logout();
	}
}
