package presenters;

import views.IReportView;

/**
 * Presenter to handle any logic related to report.
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
 * @return reportview the view.
 */
    public IReportView getView() {
        return view;
    }

/**
 * onclick listener.
 */
    public void onClickView() {
        view.refresh();
    }
}
