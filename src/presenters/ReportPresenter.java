package presenters;

import views.IReportView;

/**
 * This class describes the public methods needed for 
 * report presenter.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class ReportPresenter {

	private transient final IReportView view;
	/**
	 * Constructor for Bank Account 
	 * 
	 * @param view the view of the report
	 */
	public ReportPresenter(final IReportView view) {
		this.view = view;
	}
	
	/**
	 * @return the view
	 */
	public IReportView getView() {
		return view;
	}
	/**
	 * void method to refresh the view
	 */
	public void onClickView(){
		view.refresh();
	}

}
