package Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/VerifyOTPController")
public class VerifyOTPController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String otp = request.getParameter("otp");
        String sessionOtp = (String) request.getSession().getAttribute("otpCode");

        System.out.println("User OTP: " + otp);
        System.out.println("Session OTP: " + sessionOtp);

        if (sessionOtp != null && sessionOtp.equals(otp)) {
            response.sendRedirect(request.getContextPath() +"/admin/resetPassword.jsp");
            System.out.println("Context Path: " + request.getContextPath());
        } else {
            request.setAttribute("error", "Mã OTP không đúng!");
            request.getRequestDispatcher("/admin/verifyOTP.jsp").forward(request, response);
        }
    }
}