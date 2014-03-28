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

	public FinancialAccountSource(final Context context) {
		dbhelper = new FinancialDBOpenHelper(context);
	}

	public void open() {
		Log.i(LOGTAG, "Account Databases opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Account Databases closed");
		database.close();
	}

	public void update(final FinancialAccountSource accountSource) {
		Log.i(LOGTAG, "Databases updated");
		dbhelper.onUpgrade(accountSource.database, 1, 1);
	}

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

	public void removeAccount(final String userID, final String displayName) {
		final String[] values = new String[] { userID, displayName };
		database.delete(FinancialDBOpenHelper.TABLE_ACCOUNTS,
				FinancialDBOpenHelper.COLUMN_ACUSERID + "=? AND "
						+ FinancialDBOpenHelper.COLUMN_DISNAME + "=?", values);
		database.delete(FinancialDBOpenHelper.TABLE_TRANS, FinancialDBOpenHelper.COLUMN_TBDNAME + " = "
				+ "'" + displayName + "'", null);
		Log.i(LOGTAG, "account deleted");
	}

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
