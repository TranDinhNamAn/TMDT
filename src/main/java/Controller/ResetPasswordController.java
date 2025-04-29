package Controller;
import DAO.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
@WebServlet("/ResetPasswordController")
public class ResetPasswordController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = (String) request.getSession().getAttribute("userEmail");

        // Kiểm tra email trong session
        if (email == null) {
            System.err.println("Email không tồn tại trong session.");
            request.setAttribute("error", "Email không tồn tại trong phiên làm việc!");
            request.getRequestDispatcher("/admin/resetPassword.jsp").forward(request, response);
            return;
        }
        System.out.println("Email trong session: " + email);

        // Kiểm tra mật khẩu nhập lại
        if (!newPassword.equals(confirmPassword)) {
            System.err.println("Mật khẩu xác nhận không khớp.");
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/admin/resetPassword.jsp").forward(request, response);
            return;
        }

        // Mã hóa mật khẩu mới
        String hashedPassword = BCrypt.hashpw(newPassword.trim(), BCrypt.gensalt(10));
        System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);

        // Cập nhật mật khẩu
        boolean updated = userDao.updatePassword(email, hashedPassword);
        System.out.println("Kết quả cập nhật mật khẩu: " + updated);
        if (!updated) {
            request.setAttribute("error", "Cập nhật mật khẩu không thành công!");
            request.getRequestDispatcher("/admin/resetPassword.jsp").forward(request, response);
            return;
        }

        // Chuyển hướng đến trang đăng nhập nếu cập nhật thành công
        request.getSession().invalidate(); // Xóa toàn bộ session sau khi
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
    }
}