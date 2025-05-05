package Controller;

import DAO.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=== Bắt đầu xử lý đăng ký ===");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");


        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);

        // Kiểm tra rỗng
        if (username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                confirmPassword == null || confirmPassword.trim().isEmpty()) {
            System.out.println("Thông tin đăng ký không hợp lệ!");
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu không khớp
        if (!password.equals(confirmPassword)) {
            System.out.println("Mật khẩu không khớp!");
            request.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }

        // Kiểm tra username/email trùng lặp
        if (userDao.findUserByUsernameOrEmail(username) != null) {
            System.out.println("Username đã tồn tại.");
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }

        if (userDao.findUserByUsernameOrEmail(email) != null) {
            System.out.println("Email đã tồn tại.");
            request.setAttribute("error", "Email đã tồn tại!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email không được để trống!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }


        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println("Password (hashed): " + hashedPassword);


        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Tên đăng nhập không được để trống!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
            return;
        }

        // Đảm bảo name không bị null
        String name = (username != null && !username.trim().isEmpty()) ? username : "Người dùng chưa đặt tên";

//
        User newUser = new User(
                email,
                username,
                hashedPassword,
                false, // Giá trị mặc định cho isBlocked
                User.Provider.LOCAL, // Giá trị cho provider
                "https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain" // ProfilePicture
        );

        // Gọi registerUser
        boolean isRegistered = userDao.registerUser(newUser);
        if (isRegistered) {
            System.out.println("Đăng ký thành công, chuyển hướng sang login.jsp.");
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        } else {
            System.out.println("Đăng ký thất bại!");
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            request.getRequestDispatcher("/admin/register.jsp").forward(request, response);
        }
    }
}