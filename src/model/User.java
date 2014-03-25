package model;

import fiveminions.financialreportmvpversion.MainActivity;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * This class allows a bank user to access and change his 
 * or her information such as get his or her name, change
 * account password and so forth. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class User implements Parcelable{

	private String userID;
	private String password;
	private String name;
	private String email;
	// create a NULL_USER in case of trying to get user by a invalid useriD
	static public final User NULL_USER = new User("", "", "", "");
	static public final User ADMIN = new User("admin", "pass123",
			"Administrator", "admin@gatech.edu");

	/*******************************
	 * Default Constructor, makes a new User
	 */
	public User() {
		userID = "";
		password = "";
		name = "";
		email = "";
	}

	/*********************************
	 * constructor
	 * 
	 * @param userID
	 * @param password
	 * @param name
	 */
	public User(String userID, String password, String name, String email) {
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	/**************************
	 * getPassword method
	 * 
	 * @return password - the password for the user
	 */
	public String getPassword() {
		return password;
	}

	/***********************
	 * setPassword method
	 * 
	 * @param password
	 *            - the password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/************************
	 * getName method
	 * 
	 * @return name - the user's name
	 */
	public String getName() {
		return name;
	}

	/************************
	 * setName method
	 * 
	 * @param name
	 *            - the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*************************
	 * getUserID method
	 * 
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}
	/*************************
	 * @param userid
	 * set User ID
	 * 
	 */
	public void setUserID(String uid) {
		this.userID = uid;
	}

	/*******************************
	 * 
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/*******************************
	 * 
	 * set user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * toString method which return user name and ID
	 * 
	 * @return user name and ID 
	 */
	public String toString() {
		return name + " : " + userID;
	}
	/**
	 * describeContents method which returns content
	 * 
	 * @return 0
	 */
	@Override
	public int describeContents() {
		return 0;
	}
	/**
	 * Constructor for User
	 * 
	 * @param in 
	 */
	public User(Parcel in) {
		userID = in.readString();
		password = in.readString();
		name = in.readString();
		email = in.readString();
	}
	/**
	 * writeToParcel method which writes the information of
	 * the user to parcel
	 * 
	 * @param dest
	 * @param flag
	 */
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(userID);
		dest.writeString(password);
		dest.writeString(name);
		dest.writeString(email);
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

		@Override
		public User createFromParcel(Parcel arg0) {
			Log.i(MainActivity.LOGTAG, "createFromParcel");
			return new User(arg0);
		}

		@Override
		public User[] newArray(int arg0) {
			Log.i(MainActivity.LOGTAG, "newArray");
			return new User[arg0];
		}

	};
}
