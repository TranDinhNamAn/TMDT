package Controller;

import DAO.CartDB;
import Model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/add-to-cart")
public class AddToCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println("id: " + productId);
        // Lấy session
//        HttpSession session = request.getSession();
//        int userID =(int) session.getAttribute("userID");
        int userID = 4;
        // Lấy giỏ hàng từ session, nếu chưa có thì tạo mới
        CartDB cartDB = new CartDB();
        Cart cart = cartDB.getCartItemByUserAndProduct(userID,productId);
        response.setContentType("text/plain");
        if (cart == null) {
            cartDB.insertCartItem(userID, productId, 1, new Date());
            response.getWriter().write("Thêm sản phẩm vào giỏ hàng thành công!");
        } else {
            response.getWriter().write("Sản phẩm đã có trong giỏ hàng!");
        }
    }
}
