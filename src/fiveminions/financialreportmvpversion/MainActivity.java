package fiveminions.financialreportmvpversion;




import presenters.MainPresenter;
import views.IMainView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity implements IMainView{

	private MainPresenter mainPresenter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainPresenter = new MainPresenter(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 *  Method defined in activity_main.xml button1
	 * @param v
	 */
	public void onRegisterButtonClick(View v){
		mainPresenter.onRegisterClick();
	}

	/**
	 *  Method defined in activity_main.xml button2
	 * @param v
	 */
	public void onLoginButtonClick(View v){
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

}
