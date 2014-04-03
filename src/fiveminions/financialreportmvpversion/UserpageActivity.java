package fiveminions.financialreportmvpversion;


import java.util.ArrayList;
import java.util.List;

import model.User;
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

/**
 * This subclass of ListActivity describes the methods 
 * needed for the activity of user page to allow user 
 * the menu to be set. 
 * @version 1.0
 * @author Team 23
 */
public class UserpageActivity extends ListActivity {
    /**
     * user.
     */
    private User user; // NOPMD by wen on 4/2/14 1:28 AM
    /**
     * a list of user.
     */
    private List<String> menu; // NOPMD by wen on 4/2/14 1:28 AM
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:27 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        Bundle b = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:28 AM
        user = b.getParcelable("model.User");

        menu = new ArrayList<String>();
        menu.add("Transactions");
        menu.add("Spending Report");

        display();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu localmenu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_page_menu, localmenu); // NOPMD by wen on 4/2/14 1:28 AM
        return super.onCreateOptionsMenu(localmenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // Handle presses on the action bar items
        switch(item.getItemId()) {
            case R.id.user_page_account:
                Intent intentAcc = new Intent(this, AccountPageActivity.class);
                intentAcc.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                startActivity(intentAcc);
                break;      
            case R.id.user_page_logout:
                Intent intentLogout = new Intent(this, LoginActivity.class);
                startActivity(intentLogout);
                break;                
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * refresh list.
     */
    public void display() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view1, menu);
        setListAdapter(adapter);
        Log.i(MainActivity.LOGTAG, "Refresh Account List");
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) { // NOPMD by wen on 4/2/14 1:27 AM
        super.onListItemClick(l, v, position, id);
        switch(position) {
            case 0:
                Intent intent = new Intent(this, TransactionActivity.class);
                intent.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                startActivity(intent);
                break;
            case 1:
                Intent spendingIntent = new Intent(this, ReportTransActivity.class);
                spendingIntent.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                startActivity(spendingIntent);
                break;
            default:
                break;
        }

    }

}
