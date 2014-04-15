package views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.AbstractReport;
import model.SpendingReport;
import model.Transaction;
import model.User;
import database.FinancialReportGenerator;
import fiveminions.financialreportmvpversion.R;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This subclass of Fragment contains the methods to 
 * access the view and to set the activity. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class HomeFragment extends Fragment {
	private AbstractReport spendingReport;
	private FinancialReportGenerator datasource;
	private Map<String, Double> spendingCategory;
	private List<Transaction> spendingList;
	private String yearmonth;
	private Bundle bundle;
	private User user;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  // NOPMD by hailin on 4/2/14 9:11 PM
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView; // NOPMD by hailin on 4/2/14 9:11 PM
    }

/**
 * override android method.
 * @param savedInstanceState saved instance state
 */
    public void onActivityCreated(Bundle savedInstanceState) { // NOPMD by hailin on 3/28/14 11:19 PM
    	super.onActivityCreated(savedInstanceState);
    	getData();
    	setPieGraph();
    	setBarGraph();
    }

	public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	bundle = activity.getIntent().getExtras();
    	user = bundle.getParcelable("model.User");
    	datasource = new FinancialReportGenerator(activity);
    	Log.i("Food", user.getUserID());
    	datasource.open();
    }

    private void getData() {
    	spendingReport = new SpendingReport();
    	yearmonth = spendingReport.getCurrentYearMonth();
    	spendingCategory = datasource.getSpendingCategoryList(yearmonth, user.getUserID());
    }

	private void setPieGraph() {
		double food = 0;
		double rent = 0;
		double entertainment = 0;
		double shopping = 0;
		double other = 0;
		
		for (String x : spendingCategory.keySet()) {
			Log.i("Food", "123");
			switch(x) {
			case "Food":
				food = food + spendingCategory.get(x);
				Log.i("Food", String.valueOf(food));
				break;
			case "Rent":
				rent = rent + spendingCategory.get(x);
				break;
			case "Entertainment":
				entertainment = entertainment + spendingCategory.get(x);
				break;
			case "Shopping":
				shopping = shopping + spendingCategory.get(x);
				break;
			case "Other":
				other = other + spendingCategory.get(x);
				break;
			}
		}
		
		PieGraph piegraph = (PieGraph) getView().findViewById(R.id.pieGraph); // NOPMD by hailin on 3/28/14 11:19 PM
		piegraph.removeSlices();
		PieSlice slice = new PieSlice();
	    slice.setColor(Color.parseColor("#00CD00"));
	    slice.setValue((float) food);
	    slice.setTitle("test");
	    piegraph.addSlice(slice);
	    slice = new PieSlice();
	    slice.setColor(Color.parseColor("#DB7093"));
	    slice.setValue((float) rent);
	    piegraph.addSlice(slice);
	    slice = new PieSlice();
	    slice.setColor(Color.parseColor("#AA66CC"));
	    slice.setValue((float) entertainment);
	    piegraph.addSlice(slice);
	    slice = new PieSlice();
	    slice.setColor(Color.parseColor("#01C5BB"));
	    slice.setValue((float) shopping);
	    piegraph.addSlice(slice);
	    slice = new PieSlice();
	    slice.setColor(Color.parseColor("#FF7F24"));
	    slice.setValue((float) other);
	    piegraph.addSlice(slice);
	}

    private void setBarGraph() {
    	double[] month = new double[5];
    	LinkedList<String> dateList = spendingReport.getRecentYearMonth();
    	for (int i = 0; i < 5; i++) {
    		month[i] = 0;
    		String date = dateList.get(i);
    		spendingList = datasource.getSpendingList(date, user.getUserID());
    		for (Transaction t : spendingList) {
    			month[i] = month[i] + t.getAmount();
    		}
    	}
    	ArrayList<Bar> points = new ArrayList<Bar>();
    	Bar d = new Bar();
    	d.setColor(Color.parseColor("#99CC00"));
    	d.setName("Test1");
    	d.setValue((float) month[0]);
    	Bar d2 = new Bar();
    	d2.setColor(Color.parseColor("#FFBB33"));
    	d2.setName("Test2");
    	d2.setValue((float) month[1]);
    	Bar d3 = new Bar();
    	d3.setColor(Color.parseColor("#DB7093"));
    	d3.setName("Test3");
    	d3.setValue((float) month[2]);
    	Bar d4 = new Bar();
    	d4.setColor(Color.parseColor("#DB7093"));
    	d4.setName("Test4");
    	d4.setValue((float) month[3]);
    	points.add(d);
    	points.add(d2);
    	points.add(d3);
    	points.add(d4);

    	BarGraph g = (BarGraph) getView().findViewById(R.id.barGraph1);
    	g.setBars(points);
    }

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		setPieGraph();
		setBarGraph();
	}
}
