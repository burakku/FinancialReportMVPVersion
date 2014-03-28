package presenters;

import views.IReportView;


public class ReportPresenter {

	private transient final IReportView view;
	
	public ReportPresenter(final IReportView view) {
		this.view = view;
	}
	
	/**
	 * @return the view
	 */
	public IReportView getView() {
		return view;
	}

	public void onClickView(){
		view.refresh();
	}

}
