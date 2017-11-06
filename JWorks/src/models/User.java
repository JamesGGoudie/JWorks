package models;

/**
 * This class represents a User that can log into the system. A User has a name, email, and password.
 */
public class User extends DatabaseObject {

    protected String emailAddress;
    protected String password;
    protected String name;

    public User(String name, String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
