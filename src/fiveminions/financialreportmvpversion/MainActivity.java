package fiveminions.financialreportmvpversion;




import presenters.MainPresenter;
import views.IMainView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * This is an subclass of Activity which describes the 
 * public methods needed for the main activity of bank
 * account. These methods can set font of the view and 
 * can redirect users to login and register pages.  
 * @version 1.0
 * @author Team 23
 */
public class MainActivity extends Activity implements IMainView{
	public static final String LOGTAG = "Clover";
	private MainPresenter mainPresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:51 AM
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setFont();
		mainPresenter = new MainPresenter(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu); // NOPMD by wen on 4/2/14 1:51 AM
		return true;
	}

	/**
	 *  Method defined in activity_main.xml button1
	 * @param view
	 */
	public void onRegisterButtonClick(View view){
		mainPresenter.onRegisterClick();
	}

	/**
	 *  Method defined in activity_main.xml button2
	 * @param view
	 */
	public void onLoginButtonClick(View view){
		mainPresenter.onLoginClick();
	}
	
	@Override
	public void goRegister() {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		
	}

	@Override
	public void goLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		
	}
	
	public void setFont() {
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
		TextView textView = (TextView)findViewById(R.id.main_title);
		textView.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:51 AM
	}

}
