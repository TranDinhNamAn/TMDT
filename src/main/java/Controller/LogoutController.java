package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=== Đang xử lý đăng xuất ===");

        // Xóa tất cả dữ liệu session
        request.getSession().invalidate();

        // Chuyển hướng về trang đăng nhập hoặc trang chủ
        request.getSession().invalidate(); // Xóa toàn bộ session sau khi
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}