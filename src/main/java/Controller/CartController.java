package Controller;

import DAO.CartDB;
import Model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        double totalPrice = 0;
        CartDB cartDB = new CartDB();
        cartItems = cartDB.getCartByUserID(userID);
        for (Cart item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("shop-cart.jsp").forward(request, response);
    }
}
