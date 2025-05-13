package Model;

import java.util.Date;

public class UserAddresses {
    private int AddressID;
    private int UserID;
    private String fullnameReceiver;
    private String phoneReceiver;
    private String addressType;
    private boolean isPrimary;
    private String description;
    private Date createDate;
    private Date lastUpdateDate;
    private String userType;

    private Address address;

    public UserAddresses(String fullnameReceiver, int UserID, String phoneReceiver, String addressType, boolean isPrimary, String description, Date createDate, Date lastUpdateDate, String userType, Address address) {
        this.fullnameReceiver = fullnameReceiver;
        this.UserID = UserID;
        this.phoneReceiver = phoneReceiver;
        this.addressType = addressType;
        this.isPrimary = isPrimary;
        this.description = description;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.userType = userType;
        this.address = address;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int addressUserID) {
        this.UserID = UserID;
    }

    public String getFullnameReceiver() {
        return fullnameReceiver;
    }

    public void setFullnameReceiver(String fullnameReceiver) {
        this.fullnameReceiver = fullnameReceiver;
    }

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

