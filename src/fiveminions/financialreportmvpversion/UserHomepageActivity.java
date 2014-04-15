package fiveminions.financialreportmvpversion;


import model.User;
import views.HomeFragment;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This subclass of Activity describes the methods needed 
 * for the activity of user home page. These methods allows
 * user home page to be set according to the input.
 * @version 1.0
 * @author Team 23
 */
public class UserHomepageActivity extends Activity {
    
    private User user;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    /** 
     * nav drawer title.
     */
    private CharSequence mDrawerTitle;
    /**
     * used to store app title.
     */
    private CharSequence mTitle;
    /**
     * slide menu items.
     */
    private String[] navMenuTitles;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:35 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_homepage);
        
        Bundle b = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:35 AM
        user = b.getParcelable("model.User");
        
        setDrawerMenu();
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu); // NOPMD by wen on 4/2/14 1:33 AM
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) { // NOPMD by wen on 4/2/14 1:33 AM
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    /***
     * Called when invalidateOptionsMenu() is triggered.
     * @param menu pass in menu
     * @return menu the Menu
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen); // NOPMD by wen on 4/2/14 1:36 AM
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle); // NOPMD by wen on 4/2/14 1:35 AM
    }
    
    /**
     * setup the drawer menu
     */
    public void setDrawerMenu() {
    	mTitle = mDrawerTitle = getTitle();
        
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // NOPMD by wen on 4/2/14 1:36 AM
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, navMenuTitles));
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
 
        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true); // NOPMD by wen on 4/2/14 1:33 AM
        getActionBar().setHomeButtonEnabled(true); // NOPMD by wen on 4/2/14 1:35 AM
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle); // NOPMD by wen on 4/2/14 1:35 AM
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle); // NOPMD by wen on 4/2/14 1:35 AM
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:33 AM
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
   
    /**
     * link the page to corresponding menu list.
     * @param position the position in list
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
                
            case 1:
                Intent intent = new Intent(this, TransactionActivity.class);
                intent.putExtra("userid", user.getUserID()); // NOPMD by farongcheng on 4/3/14 12:34 AM
               // Log.i(MainActivity.LOGTAG, "Pass in userid");
                startActivity(intent);
                break;
            case 2:
                Intent spendingIntent = new Intent(this, ReportCategoryActivity.class);
                spendingIntent.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                spendingIntent.putExtra("reportType", "spending");
                startActivity(spendingIntent);
                break;
            case 3:
                Intent incomeIntent = new Intent(this, ReportCategoryActivity.class);
                incomeIntent.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                incomeIntent.putExtra("reportType", "income");
                startActivity(incomeIntent);
                break;
            case 4:
                Intent cashFlowIntent = new Intent(this, ReportCategoryActivity.class);
                cashFlowIntent.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                cashFlowIntent.putExtra("reportType", "cashFlow");
                startActivity(cashFlowIntent);
                break;
            case 5:
                Intent intentAcc = new Intent(this, AccountPageActivity.class);
                intentAcc.putExtra("userid", user.getUserID());
                Log.i(MainActivity.LOGTAG, "Pass in userid");
                startActivity(intentAcc);
                break;
            case 6:
                Intent intentLogin = new Intent(this, MainActivity.class);
                startActivity(intentLogin);
                break;
     
            default:
                break;
        }
        
        if (fragment == null) {
              // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        } else {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit(); // NOPMD by wen on 4/2/14 1:33 AM
 
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]); // NOPMD by wen on 4/2/14 1:35 AM
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
    
    /**
     * Customize Item click class.
     * @author Team23
     *
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // NOPMD by wen on 4/2/14 1:35 AM
            displayView(position);
        }
    }
}