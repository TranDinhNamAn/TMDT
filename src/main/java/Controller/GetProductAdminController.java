package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import DAO.ProductDAO;
import Model.Product;

@WebServlet("/admin/GetProductAdminController")
public class GetProductAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProduct();
        System.out.println(productList.size());
        request.setAttribute("products", productList);
        request.getRequestDispatcher("/admin/listProducts.jsp").forward(request, response);
    }
}
