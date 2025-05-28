package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAddress {
    private int addressUserID;
    private int addressID;
    private int userID;
    private String fullnameReceiver;
    private String phoneReceiver;
    private String addressType;
    private boolean isPrimary;
    private String description;
    private String createDate;
    private String lastUpdateDate;
    private String userType;

    // Getters and Setters
    public int getAddressUserID() {
        return addressUserID;
    }

    public void setAddressUserID(int addressUserID) {
        this.addressUserID = addressUserID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public boolean getIsPrimary() { // Changed to getIsPrimary()
        return isPrimary;
    }

    public void setIsPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    // Sử dụng SimpleDateFormat để chuyển đổi từ String sang chuẩn DATE của MySQL
    public void setCreateDate(String createDate) {
        this.createDate = formatDate(createDate);  // Chuyển đổi nếu cần
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    // Chuyển đổi từ String sang chuẩn DATE của MySQL
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = formatDate(lastUpdateDate);  // Chuyển đổi nếu cần
    }

    // Hàm chuyển đổi ngày từ String sang định dạng yyyy-MM-dd
    private String formatDate(String dateString) {
        try {
            // Đây là định dạng đúng với chuỗi "2025-05-01"
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");  // Định dạng chuẩn của MySQL
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
