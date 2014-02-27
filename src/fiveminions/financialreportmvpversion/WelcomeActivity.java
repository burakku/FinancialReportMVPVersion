package fiveminions.financialreportmvpversion;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
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
