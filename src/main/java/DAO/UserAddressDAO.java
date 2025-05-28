package DAO;

import Model.UserAddress;
import org.jdbi.v3.core.Jdbi;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserAddressDAO {
    private final Jdbi jdbi = Database.DatabaseConnection.getJdbi();

    public boolean insertUserAddress(UserAddress userAddress) {
        String sql = "INSERT INTO useraddresses (UserID, AddressID, FullnameReceiver, PhoneReceiver, AddressType, IsPrimary, Description, CreateDate, LastUpdateDate, UserType) " +
                "VALUES (:userID, :addressID, :fullnameReceiver, :phoneReceiver, :addressType, :isPrimary, :description, :createDate, :lastUpdateDate, :userType)";
        return jdbi.withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("userID", userAddress.getUserID())
                        .bind("addressID", userAddress.getAddressID())
                        .bind("fullnameReceiver", userAddress.getFullnameReceiver())
                        .bind("phoneReceiver", userAddress.getPhoneReceiver())
                        .bind("addressType", userAddress.getAddressType())
                        .bind("isPrimary", userAddress.getIsPrimary())  // Use getIsPrimary() method here
                        .bind("description", userAddress.getDescription())
                        .bind("createDate", convertStringToDate(userAddress.getCreateDate()))  // Convert string to Date
                        .bind("lastUpdateDate", convertStringToDate(userAddress.getLastUpdateDate()))  // Convert string to Date
                        .bind("userType", userAddress.getUserType())
                        .execute() > 0
        );
    }

    public boolean saveOrUpdate(UserAddress userAddress) {
        if (userAddress.getAddressUserID() > 0) {
            return updateUserAddress(userAddress);
        } else {
            return insertUserAddress(userAddress);
        }
    }

    // Update method for updating existing records
    public boolean updateUserAddress(UserAddress userAddress) {
        String sql = "UPDATE useraddresses SET FullnameReceiver = :fullnameReceiver, PhoneReceiver = :phoneReceiver, AddressType = :addressType, " +
                "IsPrimary = :isPrimary, Description = :description, LastUpdateDate = :lastUpdateDate, UserType = :userType " +
                "WHERE AddressUserID = :addressUserID";
        return jdbi.withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("fullnameReceiver", userAddress.getFullnameReceiver())
                        .bind("phoneReceiver", userAddress.getPhoneReceiver())
                        .bind("addressType", userAddress.getAddressType())
                        .bind("isPrimary", userAddress.getIsPrimary())  // Use getIsPrimary() method here
                        .bind("description", userAddress.getDescription())
                        .bind("lastUpdateDate", convertStringToDate(userAddress.getLastUpdateDate()))  // Convert string to Date
                        .bind("userType", userAddress.getUserType())
                        .bind("addressUserID", userAddress.getAddressUserID())
                        .execute() > 0
        );
    }

    public UserAddress getUserAddressByUserId(int userId) {
        String sql = "SELECT * FROM useraddresses WHERE UserID = :userID LIMIT 1";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userID", userId)
                        .mapToBean(UserAddress.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public java.sql.Date convertStringToDate(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = format.parse(text);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
