package Controller;

import DAO.CartDB;
import Model.Cart;
import Model.Order;
import Model.OrderDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Payment", value = "/payment")
public class PaymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = 4;
        List<Cart> cartItems = null;
        double totalPrice = 0;
        CartDB cartDB = new CartDB();
        cartItems = cartDB.getCartByUserID(userID);
        for (Cart item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("cartItems", cartItems);
        request.getSession().setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // 1. Lấy thông tin người dùng từ form
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String note = request.getParameter("note");
            String paymentMethod = request.getParameter("paymentMethod");

            // 2. Giả sử đã lấy được userID từ session (giả định bạn đã có User login)
            int userID = 4;

            // 3. Lấy giỏ hàng từ attribute (hoặc session)
            List<Cart> cartItems = (List<Cart>) request.getSession().getAttribute("cartItems");
            if (cartItems == null || cartItems.isEmpty()) {
                response.sendRedirect("payment");
                return;
            }

            // 4. Tính tổng tiền
            double totalAmount = 0;
            for (Cart item : cartItems) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            // 5. Tạo danh sách OrderDetail
            List<OrderDetail> orderDetails = new ArrayList<>();
            Date now = new Date();
            for (Cart item : cartItems) {
                OrderDetail detail = new OrderDetail(item.getProductID(), item.getQuantity(), item.getPrice() * item.getQuantity(), new Date());
                orderDetails.add(detail);
            }

            // 6. Tạo đối tượng Order
            Order order = new Order(userID, fullName, Integer.parseInt(phone), "Pending", note, now, address, paymentMethod, totalAmount, orderDetails);
            System.out.println("tổng tiến: " + totalAmount + "cart size: " + cartItems.size() + "fullName: " + fullName + "address: " + address + "paymentMethod: " + paymentMethod);

            // 7. Gọi DAO để lưu vào DB
            CartDB dao = new CartDB();
            boolean success = dao.insertOrderWithDetails(order);

            if (success) {
                dao.deleteCartByUserID(4);
                response.sendRedirect("shopping-cart");
            } else {
                request.setAttribute("error", "Lỗi khi đặt hàng. Vui lòng thử lại.");
                request.getRequestDispatcher("payment").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("payment").forward(request, response);
        }
    }
}
