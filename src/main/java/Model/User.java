package Model;

import java.util.Date;
import java.util.List;

public class User {
    private int UserID;
    private String Email;
    private String PhoneNumber;
    private String UserName;
    private String Epassword;
    private boolean IsBlocked;
    private String Status;
    private int Point;
    private Date CreateDate;
    private Date LastUpdateDate;
    private Date LastLoginDate;
    private Date SocialID;
    private List<Address> Addresses;

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

    public Date getSocialID() {
        return SocialID;
    }

    public void setSocialID(Date socialID) {
        SocialID = socialID;
    }

    public List<Address> getAddresses() {
        return Addresses;
    }

    public void setAddresses(List<Address> addresses) {
        Addresses = addresses;
    }
}
