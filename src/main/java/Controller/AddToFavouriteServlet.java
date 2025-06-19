package Controller;

import DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-favourite")
public class AddToFavouriteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productID");
        int userId = 4;
        System.out.println("Product ID nhận được: " + productId);
        try {

            ProductDAO dao = new ProductDAO();
            boolean exists = dao.isFavourite(userId, Integer.parseInt(productId));

            if (!exists) {
                dao.addFavourite(userId, Integer.parseInt(productId));
                response.getWriter().write("added");
            } else {
                response.getWriter().write("exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("error");
        }
    }
}
