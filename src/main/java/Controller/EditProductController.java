package Controller;

import DAO.ProductDAO;
import Model.Categories;
import Model.Product;
import Utils.SaveImage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet("/admin/EditProductController")
public class EditProductController extends HttpServlet {

    // Lấy sản phẩm theo ID và chuyển sang form chỉnh sửa
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            ProductDAO dao = new ProductDAO();
            Product product = dao.getProductById(productId);
            List<Categories> categories = dao.getAllCategories();

            if (product != null) {
                request.setAttribute("product", product);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/admin/editProducts.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/GetProductAdminController");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/GetProductAdminController");
        }
    }

    // Xử lý cập nhật sản phẩm khi người dùng submit form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            String description = request.getParameter("productDescription");
            int price = Integer.parseInt(request.getParameter("productPrice"));
            int stock = Integer.parseInt(request.getParameter("productStock"));
            String category = request.getParameter("productCategoryType");
            Part filePart = request.getPart("productImages");

            // Lưu ảnh nếu có ảnh mới
            String savedImageName = null;
//            if (filePart != null && filePart.getSize() > 0) {
//                SaveImage saveImage = new SaveImage();
//                savedImageName = saveImage.saveImage(filePart);
//            }

            // Tạo đối tượng Product
            Product product = new Product(
                    productId,
                    category,
                    productName,
                    description,
                    savedImageName, // sẽ cập nhật ảnh nếu có
                    price,
                    stock,
                    null, // CreateDate không cần cập nhật
                    null  // LastUpdateDate có thể cập nhật trong DAO nếu cần
            );

            ProductDAO dao = new ProductDAO();
            boolean success = dao.editProduct(product, savedImageName);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin/GetProductAdminController");
            } else {
                request.setAttribute("error", "Cập nhật sản phẩm thất bại!");
                request.setAttribute("product", product);
                request.getRequestDispatcher("/admin/editProducts.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("/admin/editProducts.jsp").forward(request, response);
        }
    }
}
