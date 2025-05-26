package Controller;

import DAO.CartDB;
import Model.Cart;
import Model.VnpayService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/vnpay")
public class PayVnpay extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy dữ liệu từ session
        List<Cart> cartItems = (List<Cart>) session.getAttribute("cartItems");
        String fullName = (String) session.getAttribute("fullName");
        String paymentMethod = (String) session.getAttribute("paymentMethod");

        if (cartItems == null || cartItems.isEmpty()) {
            response.sendRedirect("payment");
            return;
        }

        // Tính tổng tiền
        double total = 0;
        for (Cart item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }

        long amount = (long) total; // VNPAY yêu cầu đơn vị là VNĐ * 100

        // Gọi service tạo URL VNPay
        String orderInfo = "Thanh toan don hang qua VNPay";
        VnpayService vnpayService = new VnpayService();
        String vnpayUrl = vnpayService.createPaymentUrl(orderInfo,"billpayment",amount,null,"vn",
                request.getRemoteAddr(),null,null);

        // Chuyển sang trang thanh toán VNPay
        response.sendRedirect(vnpayUrl);
    }
}
