package presenters;

import model.MemoryModel;
import views.IUserpageView;
/**
 * Presenter to handle any logic related to users'
 * information.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class UserPresenter {
	private IUserpageView userPage;
	private MemoryModel model;
	/**
	 * Constructor for user presenter
	 * 
	 * @param v the view
	 * @param m the model
	 */
	public UserPresenter(IUserpageView u, MemoryModel m) {
		userPage = u;
		model = m;
	}
}
