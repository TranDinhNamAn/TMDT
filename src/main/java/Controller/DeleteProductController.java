package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ProductDAO;

import java.io.IOException;

@WebServlet("/admin/DeleteProductController")
public class DeleteProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int productId = Integer.parseInt(idParam);

                ProductDAO productDAO = new ProductDAO();
                productDAO.deleteProduct(productId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sau khi xóa xong, chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect("GetProductAdminController");
    }
}
