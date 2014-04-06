package fiveminions.financialreportmvpversion;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import database.FinancialUserSource;
import model.User;
import presenters.LoginPresenter;
import views.ILoginView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class describes the protected and public methods 
 * needed for the activity of login which can get password, 
 * ID of the user and can redirect to user page and 
 * administrator page.  
 * @version 1.0
 * @author Team 23
 */
public class LoginActivity extends Activity implements ILoginView {
    
    private LoginPresenter loginPresenter;
    private EditText userId, password;
    private TextView resultTxt;
    private FinancialUserSource datasource;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:52 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); 
        loginPresenter = new LoginPresenter(this);
        
        AdView adView = (AdView)this.findViewById(R.id.adViewTest);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);
        
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
        datasource.close(); // NOPMD by wen on 4/2/14 1:52 AM
    }
    /**
     * check button click.
     * @param view the View
     */
    public void onLoginCheckButtonClick(View view) {
        loginPresenter.onClick();
    }
    /**
     * forgot password button click.
     * @param view the View
     */
    public void onForgotPasswordButtonClick(View view) {
        loginPresenter.onForgotPasswordClick();
    }
    
    @Override
    public String getUserid() {
        return userId.getText().toString(); // NOPMD by wen on 4/2/14 1:52 AM
    }

    @Override
    public String getUserPassword() {
        return password.getText().toString(); // NOPMD by wen on 4/2/14 1:52 AM
    }

    @Override
    public void setResultText(String text) {
        resultTxt.setText(text);
    }
    /**
     * transfer to another page.
     */
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
