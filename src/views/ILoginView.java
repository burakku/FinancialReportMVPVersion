package views;

import model.User;
/**
 * Interface to the login view to specify which
 * methods the login view class contains.
 *
 * @version 1.0
 *
 * @author Team 23
 */
public interface ILoginView {
/**
 * get user id from user's input.
 * @return userid user id
 */
String getUserid();
/**
 * get user password from user's input.
 * @return password password
 */
String getUserPassword();
/**
 * set display result text to show any error.
 * @param text text
 */
void setResultText(String text);
/**
 * redirect to user page.
 */
void goUserPage();
/**
 * search a user to check if he or she exists.
 * @param uid user id
 * @return User user
 */
User findUser(String uid);
/**
 * redirect to main page.
 */
void goAdminPage();
/**
 * direct to forgot password page.
 */
void goForgotPasswordPage();
}
