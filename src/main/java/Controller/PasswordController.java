package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;

import java.io.IOException;

@WebServlet("/PasswordController")
public class PasswordController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Kiểm tra người dùng có đăng nhập không
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu từ form
        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");

        // Kiểm tra mật khẩu mới có khớp không
        if (newPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "Mật khẩu mới không khớp!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra độ dài mật khẩu mới (khuyến nghị ≥ 8 ký tự)
        if (newPassword.length() < 8) {
            request.setAttribute("message", "Mật khẩu mới phải có ít nhất 8 ký tự!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // Lấy thông tin người dùng từ database
        User user = userService.getUserById(userId);
        if (user == null) {
            request.setAttribute("message", "Lỗi: Không tìm thấy thông tin người dùng!");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu hiện tại
        if (!BCrypt.checkpw(currentPassword, user.getEpassword())) {
            request.setAttribute("message", "Mật khẩu hiện tại không chính xác!");
            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu mới (đã mã hóa)
        boolean isUpdated = userService.updateUserPassword(user.getEmail(), newPassword);

        if (isUpdated) {
            // Đổi mật khẩu thành công -> Hủy session hiện tại & yêu cầu đăng nhập lại
            session.invalidate(); // Xóa session để bảo mật
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?message=Mật khẩu đã được cập nhật. Vui lòng đăng nhập lại!");
        } else {
            request.setAttribute("message", "Có lỗi xảy ra, vui lòng thử lại!");
            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
        }
    }
}