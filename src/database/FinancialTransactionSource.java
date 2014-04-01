package database;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;
import model.myDate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class describes the methods needed for the 
 * source of financial transaction which operates on
 * transaction's balance and add or delete transaction.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class FinancialTransactionSource {
/**
 * string variable for log cat.
 */
    public static final String LOGTAG = "CLOVER";

/**
 * database open helper.
 */
    SQLiteOpenHelper dbhelper;
    
/**
 * database.
 */
    SQLiteDatabase database;

/**
 * string variable for creating database table.
 */
    public static final String[] TRANSCOLUMNS = {
        FinancialDBOpenHelper.COLUMN_TRNAME,
        FinancialDBOpenHelper.COLUMN_TRTYPE,
        FinancialDBOpenHelper.COLUMN_TRDATE,
        FinancialDBOpenHelper.COLUMN_TRAMOUNT,
        FinancialDBOpenHelper.COLUMN_TRSTATUS,
        FinancialDBOpenHelper.COLUMN_TRRECORD,
        FinancialDBOpenHelper.COLUMN_TBDNAME,
        FinancialDBOpenHelper.COLUMN_TRUSERID
    };
    
/**
 * Constructor for FinancialTransactionSource.
 * 
 * @param context context of the transaction source
 */
    public FinancialTransactionSource(Context context) {
    	dbhelper = new FinancialDBOpenHelper(context);
    }
    
/**
 * void method to open the transaction source.
 */
    public void open() {
        Log.i(LOGTAG, "Transaction Databases opened");
        database = dbhelper.getWritableDatabase();
    }
    
/**
 * void method to close the transaction source.
 */
    public void close() {
        Log.i(LOGTAG, "Transaction Databases closed");
        database.close();
    }
    
/**
 * method to check if the transaction is added.
 * 
 * @param trans transaction
 * @return flag true or false based on the result
 */
    public boolean addTransaction(final Transaction trans) {
        boolean flag = false;
        final ContentValues values = new ContentValues();
        double newbalance = 0;
        values.put(FinancialDBOpenHelper.COLUMN_TRNAME, trans.getName());
        values.put(FinancialDBOpenHelper.COLUMN_TRTYPE, trans.getType());
        values.put(FinancialDBOpenHelper.COLUMN_TRDATE, trans.getDate().getRawDate());
        values.put(FinancialDBOpenHelper.COLUMN_TRAMOUNT, trans.getAmount());
        values.put(FinancialDBOpenHelper.COLUMN_TRSTATUS, trans.getStatus());
        values.put(FinancialDBOpenHelper.COLUMN_TRRECORD, trans.getRecordTime());
        values.put(FinancialDBOpenHelper.COLUMN_TBDNAME, trans.getBkDisName());
        values.put(FinancialDBOpenHelper.COLUMN_TRUSERID, trans.getUserid());

		
        final double total = getBalance(trans.getBkDisName(), trans.getUserid());
        if (trans.getType().equals("Withdrawl")) {
            newbalance = total - trans.getAmount();
        } else {
            newbalance = total + trans.getAmount();
        }
		
        if (newbalance > 0) {
            database.insert(FinancialDBOpenHelper.TABLE_TRANS, null, values);
            updateBalance(newbalance, trans.getBkDisName(), trans.getUserid());
            flag = true;
        } 
        Log.i(LOGTAG, "Add a new transaction " + trans.getName() + " in "
						+ trans.getBkDisName());
        return flag;
    }
    
/**
 * getTransactionList method.
 * 
 * @param bankname the name of the bank
 * @param userid the ID of the user
 * @return list transaction list
 */
    public List<Transaction> getTransactionList (String bankname, String userid) {
        List<Transaction> trs = new ArrayList<Transaction>();
        Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, TRANSCOLUMNS,
				FinancialDBOpenHelper.COLUMN_TBDNAME + " = " + "'" + bankname + "' AND "
				+ FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'" + userid + "'",
				null, null, null, null);
        Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
        return cursorTransaction(cursor, trs);
    }
    
/**
 * getBalance method.
 * 
 * @param disname the name displayed
 * @param userid the ID of the user
 * @return result the balance of the account 
 */
    public double getBalance (String disname, String userid) {
        double result = 0;
        Cursor c = database.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				FinancialAccountSource.ACCOUNTCOLUMNS, FinancialDBOpenHelper.COLUMN_DISNAME + " = "
						+ "'" + disname + "' AND " + FinancialDBOpenHelper.COLUMN_ACUSERID + " = "
						+ "'" + userid + "'", null, null, null, null);
        Log.i(LOGTAG, "Find " + c.getCount() + " rows in getBalance");
        if (c != null) {
            c.moveToFirst();
			//Balance is at the third column so index is 2
            result = c.getDouble(2);
        }
        Log.i(LOGTAG, "Get balance $" + result);
        return result;
    }
/**
 * void method to update the balance of the 
 * transaction.
 * 
 * @param nb new balance
 * @param disname the name displayed
 * @param userid the ID of the user
 */
    public void updateBalance (double nb, String disname, String userid) {
        ContentValues cd = new ContentValues();
        cd.put(FinancialDBOpenHelper.COLUMN_BALANCE, nb);
        database.update(FinancialDBOpenHelper.TABLE_ACCOUNTS, cd,
				FinancialDBOpenHelper.COLUMN_DISNAME + " = " + "'" + disname + "' AND "
				+ FinancialDBOpenHelper.COLUMN_ACUSERID + " = " + "'" + userid + "'", null);
        Log.i(LOGTAG, "Update new balance $" + nb +  " in" + disname);
    }
    
/**
 * deleteTransactionList method.
 * 
 * @param recordTime the recorded time of the delete option
 */
    public void deleteTransaction (String recordTime) {
        String[] values = new String[]{recordTime};
        database.delete(FinancialDBOpenHelper.TABLE_TRANS, FinancialDBOpenHelper.COLUMN_TRRECORD + "=?", values);
        Log.i(LOGTAG, "transaction deleted");
    }
    
/**
 * protected method that return the list of the transactions.
 * 
 * @param c cursor
 * @param trs the list of the transaction 
 * @return list transaction list
 */
    protected static List<Transaction> cursorTransaction(Cursor c, List<Transaction> trs) {
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                Transaction tr = new Transaction();
                tr.setName(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRNAME)));
                tr.setType(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRTYPE)));
                tr.setDate(new myDate(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRDATE))));
                tr.setAmount(c.getDouble(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRAMOUNT)));
                tr.setStatus(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRSTATUS)));
                tr.setRecordTime(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRRECORD)));
                tr.setBkDisName(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TBDNAME)));
                tr.setUserid(c.getString(c.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRUSERID)));
                trs.add(tr);
            }
        }
        return trs;
    }
	
}
