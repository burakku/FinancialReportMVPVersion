package fiveminions.financialreportmvpversion;


import java.util.ArrayList;
import java.util.List;

import model.User;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserpageActivity extends ListActivity {

	private TextView userName;
	private User user;
	private List<String> menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_page);
		userName = (TextView) findViewById(R.id.userPageName);
		
		Bundle b = getIntent().getExtras();
		user = b.getParcelable("model.User");
		
		menu = new ArrayList<String>();
		menu.add("Transactions");
		
		userName.setText("Hello, "+ user.getName());
		display();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.user_page_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.user_page_account:
	        	Intent intentAcc = new Intent(this, AccountPageActivity.class);
	        	intentAcc.putExtra("userid", user.getUserID());
	        	Log.i(MainActivity.LOGTAG, "Pass in userid");
	        	startActivity(intentAcc);
	        	break;
	        case R.id.user_page_logout:
	        	Intent intentLogout = new Intent(this, LoginActivity.class);
	    		startActivity(intentLogout);
	        
	        default:
	        	break;
	    }
	            return super.onOptionsItemSelected(item);
	}
	
	public void display(){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view1, menu);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Account List");
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch(position) {
		case 0:
			Intent intent = new Intent(this, TransactionActivity.class);
			intent.putExtra("userid", user.getUserID());
        	Log.i(MainActivity.LOGTAG, "Pass in userid");
			startActivity(intent);
		default:
			break;
		}

	}
	
}
