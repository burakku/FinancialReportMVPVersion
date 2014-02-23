package presenters;

import model.MemoryModel;
import model.User;
import views.ILoginView;

public class LoginPresenter {
	private ILoginView login;
	private MemoryModel model;
	
	public LoginPresenter(ILoginView l, MemoryModel m) {
		login = l;
		model = m;
		//add clickListner
	}
	
	public void onClick() {
		String userIdInput = login.getUserid();
		String passwordInput = login.getUserPassword();
		String password;
		User user;
		boolean userExist = model.checkUser(userIdInput);
		boolean verified;
		
		if (userExist) {
			user = model.getUserById(userIdInput);
			password = user.getPassword();
			verified = passwordInput.equals(password);
			
			if (verified) {
				login.goUserPage();
			}
		} else {
			//login.setResultText(//erro msg);
		}
	}
}