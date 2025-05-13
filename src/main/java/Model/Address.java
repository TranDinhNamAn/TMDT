package Model;

import java.util.Date;

public class Address {
    private int AddressID;
    private String Street;
    private String WardOrcommune;
    private String District;
    private String ProvinceOrCity;
    private String FullnameReceiver;
    private String PhoneReceiver;
    private String AddressType;
    private boolean IsPrimary;
    private Date LastUpdateDate;
    private int UserType;

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getWardOrcommune() {
        return WardOrcommune;
    }

    public void setWardOrcommune(String wardOrcommune) {
        WardOrcommune = wardOrcommune;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getProvinceOrCity() {
        return ProvinceOrCity;
    }

    public void setProvinceOrCity(String provinceOrCity) {
        ProvinceOrCity = provinceOrCity;
    }

    public String getFullnameReceiver() {
        return FullnameReceiver;
    }

    public void setFullnameReceiver(String fullnameReceiver) {
        FullnameReceiver = fullnameReceiver;
    }

    public String getPhoneReceiver() {
        return PhoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        PhoneReceiver = phoneReceiver;
    }

    public String getAddressType() {
        return AddressType;
    }

    public void setAddressType(String addressType) {
        AddressType = addressType;
    }

    public boolean isPrimary() {
        return IsPrimary;
    }

    public void setPrimary(boolean primary) {
        IsPrimary = primary;
    }

    public Date getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    @Override
    public String toString() {
        return  Street +
                ", " + WardOrcommune +
                ", " + District +
                ", " + ProvinceOrCity;
    }
}
