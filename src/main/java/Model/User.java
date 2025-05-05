package Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;
    private String Email;
    private String PhoneNumber;
    private String UserName;
    private String Epassword;
    private boolean IsBlocked;
    private String Status;
    private String Rank;
    private int Point;
    private Date CreateDate;
    private Date LastUpdateDate;
    private Date LastLoginDate;
    private String FacebookID;
    private String GoogleID;
    private Provider provider;
    private String ProfilePicture;
    private String Name;


    public enum Provider {
        FACEBOOK, GOOGLE, LOCAL
    }

    // Constructor đầy đủ
    public User(int userID, String email, String phoneNumber, String userName, String epassword, boolean isBlocked,
                String status, String rank, int point, Date createDate, Date lastUpdateDate, Date lastLoginDate,
                String facebookID, String googleID, Provider provider, String profilePicture, String name) {
        this.UserID = userID;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.UserName = userName;
        this.Epassword = epassword;
        this.IsBlocked = isBlocked;
        this.Status = status;
        this.Rank = rank;
        this.Point = point;
        this.CreateDate = createDate;
        this.LastUpdateDate = lastUpdateDate;
        this.LastLoginDate = lastLoginDate;
        this.FacebookID = facebookID;
        this.GoogleID = googleID;
        this.provider = provider;
        this.ProfilePicture = profilePicture;
        this.Name = name;
    }
    public User() {
    }

    // Constructor cho đăng ký thông thường
    public User(String email, String userName, String epassword, boolean isBlocked, Provider provider, String profilePicture) {
        this.Email = email;
        this.PhoneNumber = null;
        this.UserName = userName;
        this.Epassword = epassword;
        this.IsBlocked = false;
        this.Status = "active";
        this.Rank = "newbie";
        this.Point = 0;
        this.CreateDate = new Date();
        this.LastUpdateDate = new Date();
        this.LastLoginDate = new Date();
        this.FacebookID = null;
        this.GoogleID = null;
        this.provider = Provider.LOCAL;
        this.ProfilePicture = profilePicture;
        this.Name = null;
    }

    // Constructor cho đăng nhập Google
    public User(String email, String userName, Provider provider) {
        this.Email = email;
        this.UserName = userName != null ? userName : email;
        this.Epassword = null;
        this.PhoneNumber = null;
        this.IsBlocked = false;
        this.Status = "active";
        this.Rank = "newbie";
        this.Point = 0;
        this.CreateDate = new Date();
        this.LastUpdateDate = new Date();
        this.LastLoginDate = new Date();
        this.FacebookID = (provider == Provider.FACEBOOK) ? email : null;
        this.GoogleID = (provider == Provider.GOOGLE) ? email : null;
        this.Name = userName;
    }

    // Constructor cho đăng nhập Facebook
    public User(String email, String userName, Provider provider, String profilePicture) {
        this.Email = email;
        this.UserName = userName != null ? userName : email;
        this.Epassword = null;
        this.PhoneNumber = null;
        this.IsBlocked = false;
        this.Status = "active";
        this.Rank = "newbie";
        this.Point = 0;
        this.CreateDate = new Date();
        this.LastUpdateDate = new Date();
        this.LastLoginDate = new Date();
        this.FacebookID = (provider == Provider.FACEBOOK) ? email : null;
        this.GoogleID = (provider == Provider.GOOGLE) ? email : null;
        this.provider = Provider.FACEBOOK;
        this.ProfilePicture = profilePicture;
        this.Name = userName;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEpassword() {
        return Epassword;
    }

    public void setEpassword(String epassword) {
        Epassword = epassword;
    }

    public boolean isBlocked() {
        return IsBlocked;
    }

    public void setBlocked(boolean blocked) {
        IsBlocked = blocked;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }

    public Date getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public String getFacebookID() {
        return FacebookID;
    }

    public void setFacebookID(String facebookID) {
        FacebookID = facebookID;
    }

    public String getGoogleID() {
        return GoogleID;
    }

    public void setGoogleID(String googleID) {
        GoogleID = googleID;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
