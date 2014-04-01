package fiveminions.financialreportmvpversion;


import database.FinancialUserSource;
import model.MemoryModel;
import model.User;
import presenters.LoginPresenter;
import views.ILoginView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class describes the protected and public methods 
 * needed for the activity of login which can get password, 
 * ID of the user and can redirect to user page and 
 * administrator page.  
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class LoginActivity extends Activity implements ILoginView {
	
	private LoginPresenter loginPresenter;
	EditText userId, password;
	TextView resultTxt;
	private FinancialUserSource datasource;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); 
		loginPresenter = new LoginPresenter(this);
		
		userId = (EditText) findViewById(R.id.usernameLog);
		password = (EditText) findViewById(R.id.passwordLog);	
		resultTxt = (TextView) findViewById(R.id.loginText);
		
		
		datasource = new FinancialUserSource(this);
		datasource.open();
		//datasource.update(datasource);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	public void onLoginCheckButtonClick(View v){
		loginPresenter.onClick();
	}

	public void onForgotPasswordButtonClick(View v){
		loginPresenter.onForgotPasswordClick();
	}
	
	@Override
	public String getUserid() {
		return userId.getText().toString();
	}

	@Override
	public String getUserPassword() {
		return password.getText().toString();
	}

	@Override
	public void setResultText(String text) {
		resultTxt.setText(text);
	}
	
	public void goForgotPasswordPage() {
		Intent intent = new Intent(this, ForgotPasswordActivity.class );
		startActivity(intent);		
	}
	
	
	@Override
	public void goUserPage() {
		Intent intent = new Intent(this, UserHomepageActivity.class );
		User user = findUser(getUserid());
		intent.putExtra("model.User", user);
		startActivity(intent);		
	}

	@Override
	public User findUser(String uid) {
		return  datasource.findUser(uid);
	}

	@Override
	public void goAdminPage() {
		Intent intent = new Intent(this, AdminPageActivity.class );
		startActivity(intent);
	}
	

}
