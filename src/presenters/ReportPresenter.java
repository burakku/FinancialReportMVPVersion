package presenters;

import views.IReportView;


public class ReportPresenter {

	private final IReportView view;
	
	public ReportPresenter(IReportView view) {
		this.view = view;
	}
	
	public void onClickView(){
		view.refresh();
	}

}
