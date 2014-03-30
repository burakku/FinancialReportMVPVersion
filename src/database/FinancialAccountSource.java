package database;

import java.util.ArrayList;
import java.util.List;

import model.BankAccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class describes the public methods needed for 
 * the source of financial account which check and 
 * update the information financial account. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class FinancialAccountSource {
	public static final String LOGTAG = "CLOVER";

	transient SQLiteOpenHelper dbhelper;
	transient SQLiteDatabase database;

	public static final String[] ACCOUNTCOLUMNS = {
			FinancialDBOpenHelper.COLUMN_ACNAME,
			FinancialDBOpenHelper.COLUMN_DISNAME,
			FinancialDBOpenHelper.COLUMN_BALANCE,
			FinancialDBOpenHelper.COLUMN_MIR,
			FinancialDBOpenHelper.COLUMN_ACUSERID };
	/**
	 * Constructor for FinancialAccountSource
	 * 
	 * @param context context of the account source
	 */
	public FinancialAccountSource(final Context context) {
		dbhelper = new FinancialDBOpenHelper(context);
	}
	/**
	 * void method to open the account source
	 */
	public void open() {
		Log.i(LOGTAG, "Account Databases opened");
		database = dbhelper.getWritableDatabase();
	}
	/**
	 * void method to close the account source
	 */
	public void close() {
		Log.i(LOGTAG, "Account Databases closed");
		database.close();
	}
	/**
	 * void method to update the database.
	 * 
	 * @param fs financial source
	 */
	public void update(final FinancialAccountSource accountSource) {
		Log.i(LOGTAG, "Databases updated");
		dbhelper.onUpgrade(accountSource.database, 1, 1);
	}
	/**
	 * checkAccount method that checks if the input name
	 * matches the account displaying name 
	 * 
	 * @param uid user id
	 * @param disname name displayed
	 * @return true if matches 
	 * @param false if not match
	 */
	public boolean checkAccount(final String uid, final String disname) {
		final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				ACCOUNTCOLUMNS, FinancialDBOpenHelper.COLUMN_ACUSERID + " = "
						+ "'" + uid + "'", null, null, null, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				final String name = cursor.getString(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_DISNAME));
				if (name.equals(disname)) {
					Log.i(LOGTAG, "find exsit account in " + uid);
					return true;
				}
			}
		}
		Log.i(LOGTAG, "did not find account in " + uid);
		return false;
	}
	/**
	 * void method to add an account
	 * 
	 * @param ba bank account
	 */
	public void addAccount(final BankAccount bankAccount) {
		final ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_ACNAME, bankAccount.getName());
		values.put(FinancialDBOpenHelper.COLUMN_DISNAME, bankAccount.getDisname());
		values.put(FinancialDBOpenHelper.COLUMN_BALANCE, bankAccount.getBalance());
		values.put(FinancialDBOpenHelper.COLUMN_MIR, bankAccount.getMir());
		values.put(FinancialDBOpenHelper.COLUMN_ACUSERID, bankAccount.getUserid());
		database.insert(FinancialDBOpenHelper.TABLE_ACCOUNTS, null, values);
		Log.i(LOGTAG,
				"Add a new account " + bankAccount.getName() + "in " + bankAccount.getUserid());
	}
	/**
	 * getAccount method which get the list of the accounts
	 * of the bank
	 * 
	 * @param uid user id
	 * @return accounts accounts list
	 */
	public List<BankAccount> getAccountList(final String uid) {
		final List<BankAccount> accounts = new ArrayList<BankAccount>();
		final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				ACCOUNTCOLUMNS, FinancialDBOpenHelper.COLUMN_ACUSERID + " = "
						+ "'" + uid + "'", null, null, null, null);
		// Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				BankAccount ba = new BankAccount();
				ba.setName(cursor.getString(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_ACNAME)));
				ba.setDisname(cursor.getString(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_DISNAME)));
				ba.setBalance(cursor.getDouble(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_BALANCE)));
				ba.setMir(cursor.getDouble(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_MIR)));
				ba.setUserid(cursor.getString(cursor
						.getColumnIndex(FinancialDBOpenHelper.COLUMN_ACUSERID)));
				accounts.add(ba);
			}
		}
		return accounts;
	}
	/**
	 * void method to remove an account of the user
	 * 
	 * @param userID the ID of the user
	 * @return displayName the name displayed  
	 */
	public void removeAccount(final String userID, final String displayName) {
		final String[] values = new String[] { userID, displayName };
		database.delete(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				FinancialDBOpenHelper.COLUMN_ACUSERID + "=? AND "
						+ FinancialDBOpenHelper.COLUMN_DISNAME + "=?", values);
		database.delete(FinancialDBOpenHelper.TABLE_TRANS, FinancialDBOpenHelper.COLUMN_TBDNAME + " = "
				+ "'" + displayName + "'", null);
		Log.i(LOGTAG, "account deleted");
	}
	/**
	 * getBalance method
	 * 
	 * @param disName the name displayed
	 * @return result the amount of the balance
	 */
	public double getBalance(final String disname) {
		double result = 0;
		final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				ACCOUNTCOLUMNS, FinancialDBOpenHelper.COLUMN_DISNAME + " = "
						+ "'" + disname + "'", null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			result = cursor.getDouble(1);
		}
		return result;
	}
	
}
