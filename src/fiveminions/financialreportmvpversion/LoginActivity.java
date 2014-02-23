package fiveminions.financialreportmvpversion;

import model.MemoryModel;
import presenters.LoginPresenter;
import views.ILoginView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements ILoginView {
	
	private LoginPresenter loginPresenter;
	EditText userId, password;
	TextView resultTxt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login); 
		loginPresenter = new LoginPresenter(this, new MemoryModel());
		
		userId = (EditText) findViewById(R.id.usernameLog);
		password = (EditText) findViewById(R.id.passwordLog);		
	}
	
	public void onLoginCheckButtonClick(View v){
		loginPresenter.onClick();
	}

	@Override
	public String getUserid() {
		// TODO Auto-generated method stub
		return userId.toString();
	}

	@Override
	public String getUserPassword() {
		// TODO Auto-generated method stub
		return password.toString();
	}

	@Override
	public void setResultText(String text) {
		// TODO Auto-generated method stub
		resultTxt.setText(text);
	}

	@Override
	public void goUserPage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, UserpageActivity.class );
		startActivity(intent);		
	}

}
