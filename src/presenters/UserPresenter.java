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
	 * Constructor for user presenter.
	 * 
	 * @param v the view
	 * @param m the model
	 */
	public UserPresenter(final IUserpageView view,final MemoryModel model) {
		setUserPage(view);
		setModel(model);
	}
	/**
	 * @return the userPage
	 */
	public IUserpageView getUserPage() {
		return userPage;
	}
	/**
	 * @param userPage the userPage to set
	 */
	public void setUserPage(final IUserpageView userPage) {
		this.userPage = userPage;
	}
	/**
	 * @return the model
	 */
	public MemoryModel getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(final MemoryModel model) {
		this.model = model;
	}
}
