package DAO;

import Database.DatabaseConnection;
import Model.Address;
import Model.Roles;
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
                    handle.createUpdate("INSERT INTO users (Email, PhoneNumber, UserName, Epassword, IsBlocked, Status, Rank, Point, CreateDate, LastUpdateDate, LastLoginDate, FacebookID, GoogleID, Provider, ProfilePicture, Name) " +
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


    public boolean updateUser(User user) {
        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate("UPDATE users SET Name = :name, PhoneNumber = :phone, UserName = :username, `Rank` = :rank, ProfilePicture = :profilePicture, LastUpdateDate = NOW() WHERE UserID = :userID")
                            .bind("name", user.getName())
                            .bind("phone", user.getPhoneNumber())
                            .bind("username", user.getUserName())
                            .bind("rank", user.getRank() != null ? user.getRank() : "newbie")
                            .bind("profilePicture", user.getProfilePicture())
                            .bind("userID", user.getUserID())
                            .execute() >= 0 // luôn trả true nếu không lỗi
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

    public List<Address> getAddressesByUserId(int userId) {
        String sql = """
        SELECT 
            ua.UserID,
            a.AddressID,
            a.Street,
            a.WardOrcommune,
            a.District,
            a.ProvinceOrCity
        FROM useraddresses ua
        JOIN address a ON ua.AddressID = a.AddressID
        WHERE ua.UserID = :userId
    """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Address.class)
                        .list()
        );
    }
    public List<User> getAllUsersWithAddresses() {
        List<User> users = getAllUsers(); // lấy danh sách người dùng

        for (User user : users) {
            List<Address> addresses = getAddressesByUserId(user.getUserID()); // lấy địa chỉ theo từng user
            user.setAddress(addresses); // gán vào User
        }

        return users;
    }
    public List<Roles> getUserRole(int userId) {
        String sql = "SELECT r.roleID, r.roleName " +
                "FROM users u " +
                "JOIN employees e ON u.UserID = e.UserID " +
                "JOIN user_roles ur ON e.EmployeeID = ur.EmployeeID " +
                "JOIN roles r ON ur.roleID = r.roleID " +
                "WHERE u.UserID = :userId";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userId", userId)
                        .mapToBean(Roles.class)
                        .list()
        );
    }
    public List<User> getEmployee() {
        String sql = "SELECT DISTINCT u.UserID, u.Name, u.Username, u.Email, u.PhoneNumber " +
                "FROM users u " +
                "INNER JOIN employees e ON u.UserID = e.UserID " +
                "LEFT JOIN user_roles ur ON e.EmployeeID = ur.EmployeeID " +
                "LEFT JOIN roles r ON ur.roleID = r.roleID " +
                "WHERE u.UserID NOT IN ( " +
                "    SELECT u2.UserID " +
                "    FROM users u2 " +
                "    INNER JOIN employees e2 ON u2.UserID = e2.UserID " +
                "    INNER JOIN user_roles ur2 ON e2.EmployeeID = ur2.EmployeeID " +
                "    INNER JOIN roles r2 ON ur2.roleID = r2.roleID " +
                "    WHERE r2.roleName = 'Admin' " +
                ")";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(User.class)
                        .list()
        );
    }

    public int addEmployee(int userId) {
        String sqlInsert = "INSERT INTO employees (UserID) VALUES (:userId)";
        return jdbi.withHandle(handle -> {
            // Thực hiện insert
            handle.createUpdate(sqlInsert)
                    .bind("userId", userId)
                    .execute();

            // Lấy employeeID tự động tăng vừa tạo
            Integer employeeId = handle.createQuery("SELECT LAST_INSERT_ID()")
                    .mapTo(Integer.class)
                    .one();

            return employeeId != null ? employeeId : -1;
        });
    }

    public List<Roles> getAllRoles() {
        String sql = "SELECT roleID, roleName FROM roles WHERE roleName != 'Admin'";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Roles.class)
                        .list()
        );
    }
    public int getRoleIdByName(String roleName) {
        String sql = "SELECT RoleID FROM roles WHERE RoleName = :roleName";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("roleName", roleName)
                        .mapTo(Integer.class)
                        .findOne()
                        .orElse(-1)
        );
    }
    public void addRoles(int employeeId, int roleId) {
        String sql = "INSERT INTO user_roles (EmployeeID, RoleID) VALUES (:employeeId, :roleId)";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("employeeId", employeeId)
                        .bind("roleId", roleId)
                        .execute()
        );
    }
    public void updateUser(int userId, String username, String name, String email, String phone) {
        String sql = "UPDATE users SET UserName = :username, Name = :name, Email = :email, PhoneNumber = :phone WHERE UserID = :userId";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("username", username)
                        .bind("name", name)
                        .bind("email", email)
                        .bind("phone", phone)
                        .bind("userId", userId)
                        .execute()
        );
    }

    public void clearUserRoles(int employeeId) {
        String sql = "DELETE FROM user_roles WHERE EmployeeID = :employeeId";
        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("employeeId", employeeId)
                        .execute()
        );
    }
    public int getEmployeeIdByUserId(int userId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT EmployeeID FROM employees WHERE UserID = :userId")
                        .bind("userId", userId)
                        .mapTo(Integer.class)
                        .findOne()
                        .orElseThrow(() -> new RuntimeException("Employee not found for userId: " + userId))
        );
    }

    public void deleteEmployee(int empID) {
        jdbi.useHandle(handle -> {

            handle.createUpdate("DELETE FROM employees WHERE UserID = :id")
                    .bind("id", empID)
                    .execute();
        });
    }

}