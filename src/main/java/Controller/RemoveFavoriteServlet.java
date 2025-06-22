package Controller;

import DAO.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/remove-favorite")
public class RemoveFavoriteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
//            HttpSession session = request.getSession();
//            Integer userId = (Integer) session.getAttribute("userID");
//            if (userId == null) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
            int userId = 4;
            int productId = Integer.parseInt(request.getParameter("productId"));

            System.out.println(productId);

            ProductDAO dao = new ProductDAO();
            dao.removeFavourite(userId, productId);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Xoá thất bại");
        }
    }
}
