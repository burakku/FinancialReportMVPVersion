package fiveminions.financialreportmvpversion;


import java.text.NumberFormat;

import database.FinancialTransactionSource;
import model.Transaction;
import model.MyDate;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * This class describes the methods needed for the activity 
 * of transaction detail. This class contains the detailed 
 * information including transaction bank name, type, status
 * and so forth.  
 * @version 1.0
 * @author Team 23
 */
public class TransactionDetailActivity extends Activity {
    
    private TextView tranBkname;
    private TextView tranName;
    private TextView tranType;
    private TextView tranStatus;
    private TextView tranDate;
    private TextView tranAmount;
    private TextView tranCategory;
    private Transaction tran;
    private MyDate date;
    private FinancialTransactionSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:43 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail_activity);
        
        tranBkname = (TextView) findViewById(R.id.tran_bkname);
        tranName = (TextView) findViewById(R.id.tran_name);
        tranType = (TextView) findViewById(R.id.tran_type);
        tranStatus = (TextView) findViewById(R.id.tran_status);
        tranDate = (TextView) findViewById(R.id.tran_date);
        tranAmount = (TextView) findViewById(R.id.tran_amount);
        tranCategory = (TextView) findViewById(R.id.tran_category);
        Bundle b = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:43 AM
        tran = b.getParcelable("model.Transaction");
        date = b.getParcelable("model.myDate");
        setFont();
        display();
        
        datasource = new FinancialTransactionSource(this);
        datasource.open();
    }
    /**
     * set font method.
     */
    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLight.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
        
        TextView textView1 = (TextView) findViewById(R.id.transD_textView1);
        TextView textView2 = (TextView) findViewById(R.id.transD_textView2);
        TextView textView3 = (TextView) findViewById(R.id.transD_textView3);
        TextView textView4 = (TextView) findViewById(R.id.transD_textView4);
        TextView textView6 = (TextView) findViewById(R.id.transD_textView5);
        TextView textView7 = (TextView) findViewById(R.id.transD_textView6);
        TextView textView5 = (TextView) findViewById(R.id.transD_title);
        textView1.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:43 AM
        textView2.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:44 AM
        textView3.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:43 AM
        textView4.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:43 AM
        textView5.setTypeface(typeface1); // NOPMD by wen on 4/2/14 1:43 AM
        textView6.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:43 AM
        textView7.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:43 AM
    }
    /**
     * set up all text fields.
     */
    private void display() {
    	NumberFormat numForm = NumberFormat.getCurrencyInstance();
        tranBkname.setText(tran.getBkDisName());
        tranName.setText(tran.getName());
        tranType.setText(tran.getType());
        tranStatus.setText(tran.getStatus());
        tranDate.setText(date.toString());
        tranAmount.setText(numForm.format(tran.getAmount()));
        tranCategory.setText(tran.getCategory());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.transaction_detail_acitivity, menu); // NOPMD by wen on 4/2/14 1:43 AM
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_trans) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to delete this transaction?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    datasource.deleteTransaction(tran.getRecordTime());
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
            alert.show(); // NOPMD by wen on 4/2/14 1:43 AM
        }
        return super.onOptionsItemSelected(item);
    }
}
