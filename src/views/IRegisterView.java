package views;

import model.User;

/**
 * Interface to the view for registration.
 *
 * @version 1.0
 *
 * @author Team 23
 */
public interface IRegisterView {
/**
 * get user id from user's input.
 * * @return userid
 */
    String getUserid();

/**
 * get user password from user's input.
 * @return password
 */
    String getPassword();

/**
 * get user name from user's input.
 * @return name
 */
    String getName();

/**
 * get user email from user's input.
 * @return email
 */
    String getEmail();

/**
 * set display result text to show any error.
 * @param text text
 */
    void setRegisterText(String text);

/**
 * addUser into model.
 * @param user user
 */
    void addUser(User user);

/**
 * Find user by given param.
 * @param uid userid
 * @return user
 */
    User findUser(String uid);

/**
 * redirect to loging page.
 */
    void goLoginPage();
}
