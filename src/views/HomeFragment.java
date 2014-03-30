package views;

import fiveminions.financialreportmvpversion.R;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
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
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // NOPMD by hailin on 3/28/14 11:19 PM
            Bundle savedInstanceState) { // NOPMD by hailin on 3/28/14 11:19 PM
  
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
          
        return rootView;
    }
	
	public void onActivityCreated(Bundle savedInstanceState) { // NOPMD by hailin on 3/28/14 11:19 PM
		super.onActivityCreated(savedInstanceState);
		
		final PieGraph piegraph = (PieGraph)getView().findViewById(R.id.pieGraph); // NOPMD by hailin on 3/28/14 11:19 PM
		PieSlice slice = new PieSlice();
		slice.setColor(Color.parseColor("#99CC00"));
		slice.setValue(2);
		slice.setTitle("test");
		piegraph.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#FFBB33"));
		slice.setValue(3);
		piegraph.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(Color.parseColor("#AA66CC"));
		slice.setValue(8);
		piegraph.addSlice(slice);
	}

}
