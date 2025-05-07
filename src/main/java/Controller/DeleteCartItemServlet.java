package Controller;

import DAO.CartDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-cart-item")
public class DeleteCartItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            System.out.println("productID: " + productID);
            int userID = 4;

            CartDB cartDB = new CartDB();
            boolean deleted = cartDB.deleteCartItem(userID, productID);

            if (deleted) {
                response.sendRedirect("shopping-cart");
            } else {
                response.getWriter().println("Không thể xóa sản phẩm khỏi giỏ hàng.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Đã xảy ra lỗi khi xóa sản phẩm.");
        }
    }
}