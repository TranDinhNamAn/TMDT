package Controller;

import DAO.ProductDAO;
import Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/SearchProductController")
public class SearchProductController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String keyword = request.getParameter("keyword");
        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.searchProductsByName(keyword);

        PrintWriter out = response.getWriter();
        for (Product p : products) {
            out.println("<tr>");
            out.println("<td>" + p.getCategories() + "</td>");
            out.println("<td>" + p.getNameProduct() + "</td>");
            out.println("<td>" + String.format("%,d", (long) p.getPrice()) + " VNĐ</td>");
            out.println("<td>" + p.getStock() + "</td>");
            out.println("<td>" + p.getCreateDate() + "</td>");
            out.println("<td>" + p.getLastUpdateDate() + "</td>");
            out.println("<td><img src='" + request.getContextPath() + "/Image/" + p.getImage() +
                    "' style='width: 60px; height: 60px; object-fit: cover;'></td>");
            out.println("<td>");
            out.println("<a href='EditProductController?id=" + p.getProductID() + "' class='text-warning me-2'><i class='fa fa-edit'></i></a>");
            out.println("<a href='DeleteProductController?id=" + p.getProductID() + "' class='text-danger' onclick=\"return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');\"><i class='fa fa-trash'></i></a>");
            out.println("</td>");
            out.println("</tr>");
        }

        if (products.isEmpty()) {
            out.println("<tr><td colspan='8' class='text-center'>Không tìm thấy sản phẩm nào</td></tr>");
        }
    }
}
