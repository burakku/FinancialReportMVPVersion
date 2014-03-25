package model;
import fiveminions.financialreportmvpversion.MainActivity;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * This class describes the public methods needed for 
 * a bank account, which holds the information of the
 * account of a bank user including name, user ID, balance,
 * monthly interest rate and so forth.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class BankAccount implements Parcelable{
	private String name;
	private String disname;
	private double balance;
	private double mir;
	private String userid;
	
	static public final BankAccount NULL_ACCOUNT = new BankAccount("", "", 0,0,"null");
	/**
	 * Default constructor for Bank Account 
	 *
	 */
	public BankAccount(){
		this.name = "";
		this.disname = "";
		this.balance = 0;
		this.mir = 0;
		this.userid = "";
	}
	/**
	 * Constructor for Bank Account 
	 * 
	 * @param name the name of the person
	 * @param disname the name displayed
	 * @param balance the balance of the account
	 * @param mir the monthly interest rate
	 * @param userid the id of the person
	 */
	public BankAccount(String name, String disname, double balance, double mir,
			String userid) {
		super();
		this.name = name;
		this.disname = disname;
		this.balance = balance;
		this.mir = mir;
		this.userid = userid;
	}
	/**
	 * getName method
	 * 
	 * @return name - the name of the user
	 */
	public String getName() {
		return name;
	}
	/**
	 * setName method which sets the name of the user
	 * 
	 * @return name - the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getDisname method
	 * 
	 * @return disname - the name of the user displayed on screen
	 */
	public String getDisname() {
		return disname;
	}
	/**
	 * setDisname method which sets the name of the user displayed on screen
	 * 
	 * @param disname - the name of user displayed
	 */
	public void setDisname(String disname) {
		this.disname = disname;
	}
	/**
	 * getBalance method
	 * 
	 * @return balance - the balance of the account
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * setBalance method which sets the balance of the account
	 * 
	 * @param balance - the balance of the account
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	/**
	 * getMir method
	 * 
	 * @return mir - monthly interest rate
	 */
	public double getMir() {
		return mir;
	}
	/**
	 * setMir method which sets the mir of the account
	 * 
	 * @param mir - monthly interest rate
	 */
	public void setMir(double mir) {
		this.mir = mir;
	}
	/**
	 * getUserid method
	 * 
	 * @return userid - the id of the user 
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * setUserid method which sets the id of the user
	 * 
	 * @param userid - the id of the user
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * toString method which return user name and his or her balance
	 * 
	 * @return result - the string of user name and his or her balance 
	 */
	@Override
	public String toString() {
		String result;
		result = this.name + " : $" + this.balance;
		return result;
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
	 * Constructor for Bank Account 
	 * 
	 * @param in 
	 */
	public BankAccount(Parcel in) {
		name = in.readString();
		disname = in.readString();
		balance = in.readDouble();
		mir = in.readDouble();
		userid = in.readString();
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
		dest.writeString(name);
		dest.writeString(disname);
		dest.writeDouble(balance);
		dest.writeDouble(mir);
		dest.writeString(userid);

	}

	public static final Parcelable.Creator<BankAccount> CREATOR =
			new Parcelable.Creator<BankAccount>() {

				@Override
				public BankAccount createFromParcel(Parcel arg0) {
					Log.i(MainActivity.LOGTAG, "createFromParcel");
					return new BankAccount(arg0);
				}

				@Override
				public BankAccount[] newArray(int arg0) {
					Log.i(MainActivity.LOGTAG, "newArray");
					return new BankAccount[arg0];
				}
		
			};
}
