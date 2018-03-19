package uk.co.advmob.newsapp;

/**
 * Created by whoda on 06/03/2018.
 */

public class SingleSignOn {
    // used to create a single source of the username and password the user entered at login for other parts of the app that connect with the online database.

    private static int user_id;
    private static String email;
    private static String password;
    private static String userType;
    private static String fullName;
    private static String profilePicture;

    // setemail and setpassword called from login activity line 48/49
    // setemail and setpassword also called from ProfileActivity when setting new credentials

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        SingleSignOn.user_id = user_id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SingleSignOn.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SingleSignOn.password = password;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        SingleSignOn.userType = userType;
    }

    public static String getFullName() {
        return fullName;
    }

    public static void setFullName(String fullName) {
        SingleSignOn.fullName = fullName;
    }

    public static String getProfilePicture() {
        return profilePicture;
    }

    public static void setProfilePicture(String profilePicture) {
        SingleSignOn.profilePicture = profilePicture;
    }
}
