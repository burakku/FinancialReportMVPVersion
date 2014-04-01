package fiveminions.financialreportmvpversion;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This subclass of Activity describes the methods needed 
 * for the welcome activity transaction and aims to set up
 * the welcome view for the users.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class WelcomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
		TextView textView = (TextView)findViewById(R.id.welcome_app_name);
		textView.setTypeface(typeface);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				} catch (Exception e){
					e.printStackTrace();
				} finally{
					Intent openMainActivity = new Intent(WelcomeActivity.this, MainActivity.class);
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}
	
	protected void onPause() {
		super.onPause();
		finish();
	}
}
