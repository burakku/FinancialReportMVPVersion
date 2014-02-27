package presenters;

import model.MemoryModel;
import model.User;
import views.IRegisterView;

public class RegisterPresenter {
	private IRegisterView view;
	
	public RegisterPresenter(IRegisterView v) {
		view = v;
		//add clickListener
	}
	
	public void onClick() {
		String userId = view.getUserid();
		String password = view.getPassword();
		String name = view.getName();
		String email = view.getEmail();
		String text = "";
		
		if(userId.equals("") || password.equals("") || name.equals("")||email.equals("")){
			text = "Please fill out all fields!";
		} else if(view.findUser(userId)!= User.NULL_USER){
			text = "The username already exsit, please try another one!";
		} else {
			User newUser = new User(userId, password, name,email);
			view.addUser(newUser);
			view.goLoginPage();
		}
		view.setRegisterText(text);
	}
}
