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
/**
 * report activity view.
 */
    private final transient IReportView view;

/**
 * Constructor for report presenter.
 * 
 * @param rview the view.
 */
    public ReportPresenter(final IReportView rview) {
        view = rview;
    }
	/**
	 * Constructor for Bank Account 
	 * 
	 * @param view the view of the report
	 */
/**
 * @return reportview the view.
 */
    public IReportView getView() {
        return view;
    }
	/**
	 * void method to refresh the view
	 */
/**
 * onclick listener.
 */
    public void onClickView() {
        view.refresh();
    }
}
