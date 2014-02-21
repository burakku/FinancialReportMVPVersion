package presenters;

import views.IMainView;

public class MainPresenter {
	private IMainView main;
	
	public MainPresenter(IMainView m) {
		main = m;
	}
	
	public void onClick() {
		main.goLogin();
		main.goRegister();
	}
}
