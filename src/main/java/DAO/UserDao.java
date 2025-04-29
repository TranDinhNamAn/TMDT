package DAO;

import Database.DatabaseConnection;
import Model.User;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.util.List;

public class UserDao {
    private final Jdbi jdbi;

    public UserDao() {
        this.jdbi = DatabaseConnection.getJdbi();
    }

    // Lấy ra ds
    public List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class)
                        .list()
        );
    }

    // Tìm user bằng username hoặc email
    public User findUserByUsernameOrEmail(String input) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE UserName = :input OR Email = :input")
                        .bind("input", input)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null) // Thêm .orElse(null) để trả về trực tiếp User
        );
    }

    // Tìm user bằng email
    public User findUserByEmail(String email) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE Email = :email")
                        .bind("email", email)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null) // Thêm .orElse(null) để trả về trực tiếp User
        );
    }

    // Tìm user bằng ID
    public User findUserById(int id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE UserID = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .findOne()
                        .orElse(null) // Thêm .orElse(null) để trả về trực tiếp User
        );
    }


    // Đăng ký user mới
    public boolean registerUser(User user) {
        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate("INSERT INTO users (Email, PhoneNumber, UserName, Epassword, IsBlocked, Status, `Rank`, Point, CreateDate, LastUpdateDate, LastLoginDate, FacebookID, GoogleID, Provider, ProfilePicture, Name) " +
                                    "VALUES (:email, :phone, :username, :password, :isBlocked, :status, :rank, :point, :createDate, :lastUpdateDate, :lastLoginDate, :facebookID, :googleID, :provider, :profilePicture, :name)")
                            .bind("email", user.getEmail())
                            .bind("phone", user.getPhoneNumber() != null ? user.getPhoneNumber() : "")
                            .bind("username", user.getUserName())
                            .bind("password", user.getEpassword())
                            .bind("isBlocked", user.isBlocked())
                            .bind("status", user.getStatus() != null ? user.getStatus() : "active")
                            .bind("rank", user.getRank() != null ? user.getRank() : "newbie")
                            .bind("point", user.getPoint())
                            .bind("createDate", user.getCreateDate())
                            .bind("lastUpdateDate", user.getLastUpdateDate())
                            .bind("lastLoginDate", user.getLastLoginDate() )
                            .bind("facebookID", user.getFacebookID())
                            .bind("googleID", user.getGoogleID())
                            .bind("provider", user.getProvider() != null ? user.getProvider().toString() : "LOCAL")
                            .bind("profilePicture", user.getProfilePicture() != null ? user.getProfilePicture() : "default_avatar.png").bind("name", user.getName() != null ? user.getName() : user.getUserName() != null ? user.getUserName() : "Người dùng chưa đặt tên")
                            .execute() > 0);



        } catch (Exception e) {
            System.err.println("Lỗi khi đăng ký user: " + e.getMessage());
            return false;
        }
    }



    // Cập nhật thông tin user (không cập nhật mật khẩu)
    public boolean updateUser(User user) {
        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate("UPDATE users SET PhoneNumber = :phone, UserName = :username, Rank = :rank, ProfilePicture = :profilePicture, LastUpdateDate = NOW() WHERE Email = :email")
                            .bind("phone", user.getPhoneNumber())
                            .bind("username", user.getUserName())
                            .bind("rank", user.getRank())
                            .bind("profilePicture", user.getProfilePicture())
                            .bind("email", user.getEmail())
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật user: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật mật khẩu bằng email (dùng cho quên mật khẩu)
    public boolean updatePassword(String email, String hashedPassword) {
        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate("UPDATE users SET Epassword = :password, LastUpdateDate = NOW() WHERE Email = :email")
                            .bind("password", hashedPassword)
                            .bind("email", email)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
            return false;
        }
    }

    // Xóa user theo ID
    public boolean deleteUser(int userID) {
        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate("DELETE FROM users WHERE UserID = :userID")
                            .bind("userID", userID)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa user: " + e.getMessage());
            return false;
        }
    }



}