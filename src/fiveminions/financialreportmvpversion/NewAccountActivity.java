package fiveminions.financialreportmvpversion;

import model.BankAccount;
import database.FinancialAccountSource;
import presenters.NewAccountPresenter;
import views.INewAccountView;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class describes the methods needed for the 
 * new accounts which can get and set information of 
 * the new account including monthly interest rate
 * balance, name and so forth. 
 * @version 1.0
 * @author Team 23
 */
public class NewAccountActivity extends Activity implements INewAccountView {

    private NewAccountPresenter presenter;
    private EditText acname;
    private EditText disname;
    private EditText balance;
    private EditText mir;
    private TextView resultText;
    private Bundle boudle;
    private FinancialAccountSource datasource;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:50 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount_page);
        
        presenter = new NewAccountPresenter(this);
        datasource = new FinancialAccountSource(this);
        
        acname = (EditText) findViewById(R.id.newAcName);
        disname = (EditText) findViewById(R.id.newAcDisName);
        balance = (EditText) findViewById(R.id.newAcBalance);
        mir = (EditText) findViewById(R.id.newAcMIR);
        resultText = (TextView) findViewById(R.id.accountText);
        boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:50 AM
        
        setFont();
    }

    /**
     * set font.
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLight.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
        
        TextView textView1 = (TextView) findViewById(R.id.new_bAcc_bank);
        TextView textView2 = (TextView) findViewById(R.id.new_bAcc_disName);
        TextView textView3 = (TextView) findViewById(R.id.new_bAcc_balance);
        TextView textView4 = (TextView) findViewById(R.id.new_bAcc_MIR);
        TextView textView5 = (TextView) findViewById(R.id.new_bAcc_title);
        textView1.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:50 AM
        textView2.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:49 AM
        textView3.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:50 AM
        textView4.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:49 AM
        textView5.setTypeface(typeface1); // NOPMD by wen on 4/2/14 1:49 AM
    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        datasource.close(); // NOPMD by wen on 4/2/14 1:50 AM
    }
    
    /**
     * on submit click.
     * @param view the View
     */
    public void onSubmitClick(View view) {
        presenter.onSubmitClick();
    }


    @Override
    public String getAcName() {
        return acname.getText().toString(); // NOPMD by wen on 4/2/14 1:50 AM
    }


    @Override
    public String getDisName() {
        return disname.getText().toString(); // NOPMD by wen on 4/2/14 1:50 AM
    }


    @Override
    public String getBalance() {
        return balance.getText().toString(); // NOPMD by wen on 4/2/14 1:50 AM
    }


    @Override
    public String getMIR() {
        return mir.getText().toString(); // NOPMD by wen on 4/2/14 1:50 AM
    }
    
    @Override
    public String getUserid() {
        return boudle.getString("userid");
    }

    @Override
    public void addAccount(BankAccount bankAccout) {
        datasource.addAccount(bankAccout);
    }


    @Override
    public void goAccountPage() {
        finish();
    }

    @Override
    public void setText(String text) {
        resultText.setText(text);
        
    }

    @Override
    public boolean checkAccount() {
        return datasource.checkAccount(getUserid(), getAcName());
    }

}
