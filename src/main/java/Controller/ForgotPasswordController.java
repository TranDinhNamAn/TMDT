package Controller;

import DAO.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import service.EmailService;

import java.io.IOException;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Kiểm tra email hợp lệ
        if (email == null || email.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập email hợp lệ!");
            request.getRequestDispatcher("/admin/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Tìm người dùng theo email
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("/admin/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Tạo mã OTP chuẩn 6 chữ số
        String otpCode = String.format("%06d", new java.util.Random().nextInt(999999));
        request.getSession().setAttribute("otpCode", otpCode);
        request.getSession().setAttribute("userEmail", user.getEmail());

        // Gửi mã OTP qua email và bắt lỗi nếu có
        try {
            EmailService.sendEmail(email, "Mã xác nhận", "Mã OTP của bạn là: " + otpCode);
        } catch (Exception e) {
            System.err.println("Lỗi khi gửi email: " + e.getMessage());
            request.setAttribute("error", "Không thể gửi email xác nhận. Vui lòng thử lại!");
            request.getRequestDispatcher("/admin/forgotPassword.jsp").forward(request, response);
            return;
        }

        // Chuyển hướng đến trang xác nhận OTP
        response.sendRedirect(request.getContextPath() + "/admin/verifyOTP.jsp");
    }
}