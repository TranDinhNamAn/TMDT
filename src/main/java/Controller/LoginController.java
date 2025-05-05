package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.User;
import service.UserService;

import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private final UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");

        // Kiểm tra input rỗng
        if (usernameOrEmail == null || usernameOrEmail.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin đăng nhập!");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return;
        }

        User user = userService.login(usernameOrEmail, password);

        if (user != null) {
            // Xóa session cũ trước khi đăng nhập
            request.getSession().invalidate();

            // Tạo session mới
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("avatar", user.getProfilePicture()); // Lưu URL avatar vào session
            session.setAttribute("name", user.getName()); // Lưu tên người dùng vào session
            session.setMaxInactiveInterval(30 * 60); // Thiết lập session hết hạn sau 30 phút

            // Chuyển hướng đến trang dashboard sau khi đăng nhập thành công
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        }
    }
}