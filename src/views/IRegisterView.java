package views;

public interface IRegisterView {
	String getUserid();
	String getPassword();
	String getName();
	void setRegisterText(String text);
	void goUserPage();
}
