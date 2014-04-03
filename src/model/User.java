package model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import fiveminions.financialreportmvpversion.MainActivity;

/**
 * This class allows a bank user to access and change his or her information
 * such as get his or her name, change account password and so forth.
 * 
 * @version 1.0
 * @author Team 23
 */
public class User implements Parcelable { // NOPMD by wen on 4/2/14 1:14 AM
	/**
	 * userid.
	 */
    private String userID;
	/**
	 * password.
	 */
    private String password;
	/**
	 * name.
	 */
    private String name;
	/**
	 * email.
	 */
    private String email;
    // create a NULL_USER in case of trying to get user by a invalid useriD
    /**
     * dummy user.
     */
    public static final User NULL_USER = new User("", "", "", "");
    /**
     * admin user.
     */
    public static final User ADMIN = new User("admin", "pass123",
            "Administrator", "admin@gatech.edu");
    
    /**
     * parcelable creator.
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(final Parcel arg0) {
            Log.i(MainActivity.LOGTAG, "createFromParcel");
            return new User(arg0);
        }

        @Override
        public User[] newArray(final int arg0) {
            Log.i(MainActivity.LOGTAG, "newArray");
            return new User[arg0];
        }

    };

    /*******************************
     * Default Constructor, makes a new User.
     */
    public User() {
        userID = "";
        password = "";
        name = "";
        email = "";
    }

    /*********************************
     * constructor.
     * @param userID userid
     * @param password password
     * @param name name
     * @param email email
     */
    public User(final String userID, final String password, final String name,
            final String email) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    /**************************
     * getPassword method.
     * 
     * @return password - the password for the user
     */
    public String getPassword() {
        return password;
    }

    /***********************
     * setPassword method.
     * 
     * @param password
     *            - the password to be set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /************************
     * getName method.
     * 
     * @return name - the user's name
     */
    public String getName() {
        return name;
    }

    /************************
     * setName method.
     * 
     * @param name
     *            - the name to be set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /*************************
     * getUserID method.
     * 
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /*************************
     * 
     * userid set User ID.
     * @param uid userid
     */
    public void setUserID(final String uid) {
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
     * set user email.
     * @param email email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * toString method which return user name and ID.
     * 
     * @return user name and ID
     */
    @Override
    public String toString() {
        return name + " : " + userID;
    }

    /**
     * describeContents method which returns content.
     * 
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Constructor for User.
     * 
     * @param in parcel
     */
    public User(final Parcel in) { // NOPMD by wen on 4/2/14 1:15 AM
        userID = in.readString();
        password = in.readString();
        name = in.readString();
        email = in.readString();
    }

    /**
     * writeToParcel method which writes the information of the user to parcel.
     * 
     * @param dest destination
     * @param flag flag
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flag) {
        Log.i(MainActivity.LOGTAG, "writeToParcel");
        dest.writeString(userID);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(email);
    }

}
