package Controller;

import DAO.CartDB;
import Model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShoppingCart", value = "/shopping-cart")
public class CartController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//
//        HttpSession session = request.getSession();
//        Object userIDObj = session.getAttribute("UserID");
//        if (userIDObj == null) {
//            response.sendRedirect("login");
//            return;
//        }
//
//        int userID = (Integer) userIDObj;
//        if (userID == 0) {
//            response.sendRedirect("login");
//            return;
//        }
        int userID = 4;
        List<Cart> cartItems = null;

        CartDB cartDB = new CartDB();
        cartItems = cartDB.getCartByUserID(userID);
        System.out.println(cartItems.size());

        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("shop-cart.jsp").forward(request, response);
    }
}
