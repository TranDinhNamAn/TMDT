package service;

import DAO.UserDao;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    // Truy vấn danh sách người dùng
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers(); // Sử dụng phương thức từ UserDao
        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn danh sách người dùng: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Đăng nhập bằng username hoặc email
    public User login(String usernameOrEmail, String password) {
        User user = userDao.findUserByUsernameOrEmail(usernameOrEmail);

        if (user == null) {
            System.out.println("Không tìm thấy user với tên đăng nhập/email: " + usernameOrEmail);
            return null; // Đăng nhập thất bại
        }

        if (!BCrypt.checkpw(password, user.getEpassword())) { // Đổi từ getPassword() sang getEpassword()
            System.out.println("Mật khẩu không khớp cho user: " + usernameOrEmail);
            return null; // Đăng nhập thất bại
        }

        System.out.println("Mật khẩu nhập vào: " + password);
        System.out.println("Mật khẩu lưu trong DB: " + user.getEpassword()); // Đổi từ getPassword() sang getEpassword()

        System.out.println("Đăng nhập thành công cho user: " + usernameOrEmail);
        return user;
    }

    // Cập nhật thông tin user
    public boolean updateUser(User user) {
        // Kiểm tra logic nghiệp vụ
        if (user.getUserName() == null || user.getUserName().trim().isEmpty() || // Đổi từ getUsername() sang getUserName()
                user.getEmail() == null || user.getEmail().trim().isEmpty() ||
                user.getName() == null || user.getName().trim().isEmpty()) {
            return false;
        }
        return userDao.updateUser(user); // Đổi từ update() sang updateUser() cho phù hợp với UserDao
    }

    // Cập nhật mật khẩu
    public boolean updateUserPassword(String email, String newPassword) {
        if (newPassword == null || newPassword.trim().isEmpty()) {
            System.err.println("Mật khẩu mới không được để trống.");
            return false;
        }

        // Mã hóa mật khẩu trước khi lưu vào database
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        return userDao.updatePassword(email, hashedPassword);
    }

    // Tìm user theo ID
    public User getUserById(int id) {
        return userDao.findUserById(id);
    }
}