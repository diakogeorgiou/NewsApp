package uk.co.advmob.newsapp;

public abstract class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String profilePicture;

    public User(int id, String username, String password, String email, String fullName, String profilePicture) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFullName(fullName);
        setProfilePicture(profilePicture);
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
