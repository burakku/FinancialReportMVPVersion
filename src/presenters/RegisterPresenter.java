package presenters;

import model.MemoryModel;
import model.User;
import views.IRegisterView;

public class RegisterPresenter {
	private IRegisterView register;
	private MemoryModel model;
	private User newUser;
	
	public RegisterPresenter(IRegisterView r, MemoryModel m) {
		register = r;
		model = m;
		//add clickListener
	}
	
	public void onClick() {
		String userId = register.getUserid();
		String password = register.getPassword();
		String name = register.getName();
		String text = "";
		
		if(userId.equals("") || password.equals("") || name.equals("")){
			text = "Please fill out all fields!";
		} else if(model.getUserById(userId)!= null){
			text = "The username already exsit, please try another one!";
		} else {
			newUser = new User(userId, password, name);
			model.addUser(newUser);
			register.goUserPage();
		}
		
	}
}
