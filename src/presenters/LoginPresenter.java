package presenters;

import model.MemoryModel;
import model.User;
import views.ILoginView;

public class LoginPresenter {
	private ILoginView view;
	
	public LoginPresenter(ILoginView l) {
		view = l;
		//add clickListner
	}
	
	public void onClick() {
		String uid = view.getUserid();
		String pw = view.getUserPassword();
		String text = "";
		User user = view.findUser(uid);
		if(uid.equals("") || pw.equals("")){
			text = "Username or Password cannot be empty!";
		} else if(user==User.NULL_USER){
			text = "The userId does not exsit!";
		} else if(user.getPassword().equals(pw)){
			if(user.getUserID().equals("admin")){
				view.goAdminPage();
			} else {
				view.goUserPage();
			}
			
		} else {
			text = "The password is incorrect!";
		}
		view.setResultText(text);
	}
}