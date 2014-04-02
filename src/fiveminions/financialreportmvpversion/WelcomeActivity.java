package fiveminions.financialreportmvpversion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This subclass of Activity describes the methods needed 
 * for the welcome activity transaction and aims to set up
 * the welcome view for the users.
 * @version 1.0
 * @author Team 23
 */
public class WelcomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:26 AM
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
		TextView textView = (TextView)findViewById(R.id.welcome_app_name);
		textView.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:27 AM
		
		Thread timer = new Thread(){ // NOPMD by wen on 4/2/14 1:27 AM
			public void run(){
				try{
					sleep(3000);
				} catch (Exception e){ // NOPMD by wen on 4/2/14 1:27 AM
					e.printStackTrace(); // NOPMD by wen on 4/2/14 1:27 AM
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
		finish(); // NOPMD by wen on 4/2/14 1:27 AM
	}
}
