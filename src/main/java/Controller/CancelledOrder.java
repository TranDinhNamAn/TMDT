package Controller;

import DAO.CartDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cancel-order")
public class CancelledOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            CartDB orderDAO = new CartDB();

            boolean success = orderDAO.cancelOrder(orderID);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Đã hủy đơn hàng thành công.");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Không thể hủy đơn hàng.");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Lỗi hệ thống: " + e.getMessage());
        }
    }
}

