package presenters;

import model.MemoryModel;
import views.IUserpageView;

public class UserPresenter {
	private IUserpageView userPage;
	private MemoryModel model;
	
	public UserPresenter(IUserpageView u, MemoryModel m) {
		userPage = u;
		model = m;
	}
}
