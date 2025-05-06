package Controller;

import DAO.CartDB;
import Model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update-cart-item")
public class UpdateCartItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int userID = 4;

            CartDB cartDB = new CartDB();
            boolean updated = cartDB.updateCartItemQuantity(userID, productID, quantity);

            if (updated) {
                Cart updatedCart = cartDB.getCartItemByUserAndProduct(userID, productID);
                JsonObject json = new JsonObject();
                json.addProperty("productID", updatedCart.getProductID());
                json.addProperty("quantity", updatedCart.getQuantity());
                json.addProperty("price", updatedCart.getPrice());
                json.addProperty("total", updatedCart.getPrice() * updatedCart.getQuantity());

                out.print(new Gson().toJson(json));
            } else {
                out.print("{\"error\": true}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"error\": true}");
        } finally {
            out.flush();
            out.close();
        }
    }
}
