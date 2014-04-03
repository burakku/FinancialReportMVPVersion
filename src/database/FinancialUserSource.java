package database;

import java.util.ArrayList;
import java.util.List;

import model.User;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class describes the public methods needed for 
 * the source of financial user which can add or remove
 * user and update the information for the user data 
 * source. 
 * @version 1.0
 * @author Team 23
 */
public class FinancialUserSource {
	
/**
 * string variable for log cat.
 */	
    public static final String LOGTAG = "CLOVER";

/**
 * database open helper.
 */
    SQLiteOpenHelper dbhelper; // NOPMD by hailin on 4/2/14 9:15 PM

/**
 * database.
 */
    SQLiteDatabase database; // NOPMD by hailin on 4/2/14 9:17 PM

/**
 * string defining database columns.
 */
    protected static final String[] USERCOLUMNS = {
        FinancialDBOpenHelper.COLUMN_USERID,
        FinancialDBOpenHelper.COLUMN_PASSWORD,
        FinancialDBOpenHelper.COLUMN_NAME,
        FinancialDBOpenHelper.COLUMN_EMAIL,
        FinancialDBOpenHelper.COLUMN_TYPE
    };
	
/**
 * Constructor for FinancialUserSource.
 * 
 * @param context context of the account source
 */
    public FinancialUserSource(final Context context) {
        dbhelper = new FinancialDBOpenHelper(context);
    }
	
/**
 * void method to open the user source.
 */
    public void open() {
        Log.i(LOGTAG, "Databases opened");
        database = dbhelper.getWritableDatabase();
    }
	
/**
 * void method to open the close source.
 */
    public void close() {
        Log.i(LOGTAG, "Databases closed");
        database.close();
    }
	
/**
 * void method to update the database.
 * 
 * @param userSource financial source
 */
    public void update(final FinancialUserSource userSource) {
        Log.i(LOGTAG, "Databases updated");
        dbhelper.onUpgrade(userSource.database, 1, 1);
    }
	
/**
 * method to findUer according to the input user id.
 * 
 * @param uid the ID of the user
 * @return user the user that is founded
 */
    public User findUser(final String uid) {
        final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_USERS, USERCOLUMNS,
				null, null, null, null, null);
        final User user = new User(); // NOPMD by hailin on 4/2/14 9:18 PM
        Log.i(LOGTAG, "Find " + cursor.getCount() + " rows"); // NOPMD by hailin on 4/2/14 9:17 PM
        if (cursor.getCount() > 0) { // NOPMD by hailin on 4/2/14 9:16 PM
            while (cursor.moveToNext()) { // NOPMD by hailin on 4/2/14 9:15 PM
                if (cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_USERID)).equals(uid)) { // NOPMD by hailin on 4/2/14 9:16 PM
                    user.setUserID(uid);
                    user.setPassword(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_PASSWORD))); // NOPMD by hailin on 4/2/14 9:16 PM
                    user.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_NAME))); // NOPMD by hailin on 4/2/14 9:16 PM
                    Log.i(LOGTAG, "find user " + uid);
                    return user; // NOPMD by hailin on 4/2/14 9:16 PM
                }
            }
        }
        Log.i(LOGTAG, "find NULL_USER");
        return User.NULL_USER;
    }
	
/**
 * void method to add a user.
 * 
 * @param user the user to be added
 */
    public void addUser(final User user) {
        final ContentValues values = new ContentValues();
        values.put(FinancialDBOpenHelper.COLUMN_USERID, user.getUserID());
        values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(FinancialDBOpenHelper.COLUMN_NAME, user.getName());
        values.put(FinancialDBOpenHelper.COLUMN_EMAIL, user.getEmail());
        values.put(FinancialDBOpenHelper.COLUMN_TYPE, "user");
        database.insert(FinancialDBOpenHelper.TABLE_USERS, null, values);
        Log.i(LOGTAG, "Add a new user " + user.getUserID());
    }
	
/**
 * getUserList method to get the list of users.
 * 
 * @return users the list of the users
 */
    public List<User> getUserList() {
        final List<User> users = new ArrayList<User>();
        final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_USERS, USERCOLUMNS,
				"type_of_user = 'user'", null, null, null, null);
        User user = new User(); // NOPMD by hailin on 4/2/14 9:15 PM
        Log.i(LOGTAG, "Find " + cursor.getCount() + " rows"); // NOPMD by hailin on 4/2/14 9:18 PM
        if (cursor.getCount() > 0) { // NOPMD by hailin on 4/2/14 9:15 PM
            while (cursor.moveToNext()) { // NOPMD by hailin on 4/2/14 9:15 PM
                user.setUserID(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_USERID))); // NOPMD by hailin on 4/2/14 9:16 PM
                user.setPassword(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_PASSWORD))); // NOPMD by hailin on 4/2/14 9:15 PM
                user.setName(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_NAME))); // NOPMD by hailin on 4/2/14 9:16 PM
                user.setEmail(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_EMAIL))); // NOPMD by hailin on 4/2/14 9:18 PM
                users.add(user);
            }
        }
        return users;
    }
/**
 * void method to remove user according to the input userID.
 * 
 * @param userID the ID of the user
 */
    public void removeUser(final String userID) {
        final String[] value = new String[]{userID};
        database.delete(FinancialDBOpenHelper.TABLE_USERS, FinancialDBOpenHelper.COLUMN_USERID + "=?", value);
        database.delete(FinancialDBOpenHelper.TABLE_ACCOUNTS, FinancialDBOpenHelper.COLUMN_ACUSERID + "=?", value);
        database.delete(FinancialDBOpenHelper.TABLE_TRANS, FinancialDBOpenHelper.COLUMN_TRUSERID + "=?", value);
        Log.i(LOGTAG, "Delete user : " + userID);
    }
    
/**
 * void method to reset the password of the user.
 * 
 * @param uid the ID of the user
 */
    public void resetPW(final String uid) {
        final ContentValues values = new ContentValues();
        final String[] userid = new String[]{uid};
        values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, "123456");
        database.update(FinancialDBOpenHelper.TABLE_USERS, values, FinancialDBOpenHelper.COLUMN_USERID + "=?", userid);
        Log.i(LOGTAG, "Password reset : " + uid);
    }
}
