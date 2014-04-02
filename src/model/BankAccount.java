package model;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import fiveminions.financialreportmvpversion.MainActivity;

/**
 * This class describes the public methods needed for 
 * a bank account, which holds the information of the
 * account of a bank user including name, user ID, balance,
 * monthly interest rate and so forth.
 * @version 1.0
 * @author Team 23
 */
public class BankAccount implements Parcelable {
    private String name;
    private String disname;
    private double balance;
    private double mir;
    private String userid;
    /**
     * parcelable creator method.
     */
    public static final Parcelable.Creator<BankAccount> CREATOR =
            new Parcelable.Creator<BankAccount>() {

                @Override
                public BankAccount createFromParcel(final Parcel arg0) {
                    Log.i(MainActivity.LOGTAG, "createFromParcel");
                    return new BankAccount(arg0);
                }

                @Override
                public BankAccount[] newArray(final int arg0) {
                    Log.i(MainActivity.LOGTAG, "newArray");
                    return new BankAccount[arg0];
                }
        
            };
    /**
     * dummy bankaccount.
     */
    static public final BankAccount NULL_ACCOUNT = new BankAccount("", "", 0, 0, "null");
    /**
     * Default constructor for Bank Account.
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
     * Constructor for Bank Account .
     * @param name the name of the person
     * @param disname the name displayed
     * @param balance the balance of the account
     * @param mir the monthly interest rate
     * @param userid the id of the person
     */
    public BankAccount(final String name, final String disname, final double balance, final double mir,
            final String userid) {
        super();
        this.name = name;
        this.disname = disname;
        this.balance = balance;
        this.mir = mir;
        this.userid = userid;
    }
    /**
     * getName method.
     * 
     * @return name - the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * setName method which sets the name of the user.
     * 
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * getDisname method.
     * 
     * @return disname - the name of the user displayed on screen
     */
    public String getDisname() {
        return disname;
    }
    /**
     * setDisname method which sets the name of the user displayed on screen.
     * 
     * @param disname - the name of user displayed
     */
    public void setDisname(final String disname) {
        this.disname = disname;
    }
    /**
     * getBalance method.
     * 
     * @return balance - the balance of the account
     */
    public double getBalance() {
        return balance;
    }
    /**
     * setBalance method which sets the balance of the account.
     * 
     * @param balance - the balance of the account
     */
    public void setBalance(final double balance) {
        this.balance = balance;
    }
    /**
     * getMir method.
     * 
     * @return mir - monthly interest rate
     */
    public double getMir() {
        return mir;
    }
    /**
     * setMir method which sets the mir of the account.
     * 
     * @param mir - monthly interest rate
     */
    public void setMir(final double mir) {
        this.mir = mir;
    }
    /**
     * getUserid method.
     * 
     * @return userid - the id of the user 
     */
    public String getUserid() {
        return userid;
    }
    /**
     * setUserid method which sets the id of the user.
     * 
     * @param userid - the id of the user
     */
    public void setUserid(final String userid) {
        this.userid = userid;
    }
    /**
     * toString method which return user name and his or her balance.
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
     * describeContents method which returns content.
     * 
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * Constructor for Bank Account.
     * 
     * @param inparcel 
     */
    public BankAccount(final Parcel inparcel) {
        name = inparcel.readString();
        disname = inparcel.readString();
        balance = inparcel.readDouble();
        mir = inparcel.readDouble();
        userid = inparcel.readString();
    }
    /**
     * writeToParcel method which writes the information of
     * the user to parcel.
     * 
     * @param dest destination
     * @param flag flag
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flag) {
        Log.i(MainActivity.LOGTAG, "writeToParcel");
        dest.writeString(name);
        dest.writeString(disname);
        dest.writeDouble(balance);
        dest.writeDouble(mir);
        dest.writeString(userid);

    }

}
