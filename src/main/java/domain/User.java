package domain;

public class User {

    private String userID;
    private String userFirstName;
    private String userSecondName;
    private String userName;
    private String userPassword;
    private int userAge;
    private String userGender;
    private String userEmail;
    private String userAddress;
    private UserType userType;



    public User() { }

    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User( String userID,
                 String userFirstName,
                 String userSecondName,
                 String userName,
                 String userPassword,
                 int userAge,
                 String userGender,
                 String userEmail,
                 String userAddress,
                 UserType userType) {
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userType = userType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public  UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }
}
