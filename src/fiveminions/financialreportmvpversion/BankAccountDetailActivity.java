package fiveminions.financialreportmvpversion;


import model.BankAccount;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import database.FinancialAccountSource;

/**
 * This class describes the methods needed for the 
 * activity of bank account detail which contains
 * the details of a bank account such as setting 
 * font for the account.
 * @version 1.0
 * @author Team 23
 */
public class BankAccountDetailActivity extends Activity {
    public static final String LOGTAG = "CLOVER";
    private TextView bankname;
    private TextView accountname;
    private TextView balance;
    private TextView mir;
    private BankAccount baccount; 
    private FinancialAccountSource datasource;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:56 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_detail);
        bankname = (TextView) findViewById(R.id.bankname);
        accountname = (TextView) findViewById(R.id.accountname);
        balance = (TextView) findViewById(R.id.balance);
        mir = (TextView) findViewById(R.id.mir);
        
        Bundle boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:56 AM
        baccount = boudle.getParcelable("model.BankAccount");
        setFont();
        display();
        

        datasource = new FinancialAccountSource(this);
        datasource.open();
        
        
    }
    
    /**
     * The method to set the font.
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLight.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
        
        TextView textView1 = (TextView) findViewById(R.id.accD_balance);
        TextView textView2 = (TextView) findViewById(R.id.accD_bankname);
        TextView textView3 = (TextView) findViewById(R.id.accD_disName);
        TextView textView4 = (TextView) findViewById(R.id.accD_mir);
        TextView textView5 = (TextView) findViewById(R.id.accD_title);
        textView1.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:55 AM
        textView2.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:55 AM
        textView3.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:56 AM
        textView4.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:56 AM
        textView5.setTypeface(typeface1); // NOPMD by wen on 4/2/14 1:56 AM
    }

    /**
     * set the Text field.
     */
    private void display() {
        bankname.setText(baccount.getName());
        accountname.setText(baccount.getDisname());
        balance.setText(Double.toString(baccount.getBalance()));
        mir.setText(Double.toString(baccount.getMir()));        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bank_account_detail, menu); // NOPMD by wen on 4/2/14 1:55 AM
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delet_bank_account) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete this account?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    datasource.removeAccount(baccount.getUserid(), baccount.getDisname());
                    finish();
                }
            });
            
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();            
                }
            });
            AlertDialog alert = builder.create();
            alert.show(); // NOPMD by wen on 4/2/14 1:55 AM
        }
        return super.onOptionsItemSelected(item);
    }

}
