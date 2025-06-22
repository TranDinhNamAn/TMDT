package Controller;

import DAO.ProductDAO;
import Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/favorite-products")
public class FavProductCotroller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Giả sử bạn lấy userId từ session hoặc request
//        HttpSession session = request.getSession();
//        Integer userId = (Integer) session.getAttribute("userID");
//        if (userId == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
        int userId = 4;
        List<Product> favoriteProducts = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        favoriteProducts = dao.getFavouriteProducts(userId);

        request.setAttribute("favoriteProducts", favoriteProducts);
        request.getRequestDispatcher("wish_lists.jsp").forward(request, response);
    }
}
