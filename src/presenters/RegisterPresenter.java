package presenters;

import model.User;
import views.IRegisterView;

/**
 * Presenter to handle any logic related to users'
 * registration activities.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class RegisterPresenter {
/**
 * Register activity view.
 */
    private final transient IRegisterView view;
	
/**
 * Constructor for register presenter.
 * 
 * @param rview the view
 */
    public RegisterPresenter(final IRegisterView rview) {
        view = rview;
		//add clickListener
    }
    
/**
 * Handle the register button click in the UI,
 * Check if the all fields are filled out
 * if the user name is already exist 
 * then redirect to login page.
 */
    public void onClick() {
        final String userId = view.getUserid();
        final String password = view.getPassword();
        final String name = view.getName();
        final String email = view.getEmail();
        String text = ""; // NOPMD by hailin on 3/29/14 1:06 AM
		
        if (userId.equals("") || password.equals("") || name.equals("") || email.equals("")) { // NOPMD by hailin on 3/29/14 1:06 AM
            text = "Please fill out all fields!"; // NOPMD by hailin on 4/2/14 9:24 PM
        } if (!view.findUser(userId).equals(User.NULL_USER)) { // NOPMD by hailin on 3/29/14 1:07 AM
            text = "The username already exsit, please try another one!";
        } else {
            final User newUser = new User(userId, password, name, email);
            view.addUser(newUser);
            view.goLoginPage();
        }
        view.setRegisterText(text);
    }
}
