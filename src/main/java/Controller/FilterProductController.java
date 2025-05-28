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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FiltList", value = "/filter-list")
public class FilterProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cate = req.getParameter("categoryFilter");
        String name = req.getParameter("searchInput");
        String price = req.getParameter("priceFilter");

        List<Product> filter_list = new ArrayList<>();
        ProductDAO dao = new ProductDAO();

        if (name != null && !name.isEmpty()) {
            filter_list = dao.getProductsByName(name);
        }else if (price != null && !price.isEmpty()) {
            filter_list = dao.getProductsByPriceRange(Integer.parseInt(price));
        } else if (cate != null && !cate.isEmpty()) {
            filter_list = dao.getProductsByCategory(Integer.parseInt(cate));
        } else {
            filter_list = dao.getAllProduct();
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (filter_list.isEmpty()) {
            out.print("<p>Không tìm thấy sản phẩm phù hợp.</p>");
            return;
        }

        for (Product p : filter_list) {
            out.print(
                    "<div class='col-lg-3 col-md-4 col-sm-6 mix'>" +
                            "<div class='product__item'>" +
                            "<div class='product__item__pic set-bg' style='background-image: url(\"Image/" + p.getImage() + "\"); height: 270px; background-size: cover;'>" +
                            "<ul class='product__hover'>" +
                            "<li><a href='Image/" + p.getImage() + "' class='image-popup'>" +
                            "<img src='Image/" + p.getImage() + "' style='width: 70px; height: 70px; object-fit: cover;'>" +
                            "</a></li>" +
                            "<li><a href='#'><span class='icon_heart_alt'></span></a></li>" +
                            "<li><a href='shopping-cart.jsp'><span class='icon_bag_alt'></span></a></li>" +
                            "</ul>" +
                            "</div>" +
                            "<div class='product__item__text'>" +
                            "<h6><a href='product-detail?id=" + p.getProductID() + "'>" + p.getNameProduct() + "</a></h6>" +
                            "<div class='rating'>" +
                            "<i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i>" +
                            "</div>" +
                            "<div class='product__price'>" + p.getPrice() + " VNĐ</div>" +
                            "</div>" +
                            "</div>" +
                            "</div>"
            );
        }
    }
}
