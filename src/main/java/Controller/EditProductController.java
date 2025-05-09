package Controller;

import DAO.ProductDAO;
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

@MultipartConfig
@WebServlet("/admin/EditProductController")
public class EditProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Lấy dữ liệu từ form
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productname = request.getParameter("productName");
            String description = request.getParameter("productDescription");
            int price = Integer.parseInt(request.getParameter("productPrice"));
            int stock = Integer.parseInt(request.getParameter("productStock"));
            int category = Integer.parseInt(request.getParameter("productCategoryType"));
            Part filePart = request.getPart("productImages"); // Tên input trong form

            // Lưu ảnh nếu có ảnh mới
            String savedImageName = null;
//            if (filePart != null && filePart.getSize() > 0) {
//                SaveImage saveImage = new SaveImage();
//                savedImageName = saveImage.saveImage(filePart); // Trả về tên ảnh
//            }

            // Tạo đối tượng Product
            Product product = new Product(
                    productname,
                    description,
                    price,
                    stock,
                    category
            );

            // Gọi DAO để cập nhật
            ProductDAO dao = new ProductDAO();
            boolean success = dao.editProduct(product, savedImageName);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/admin/productList.jsp");
            } else {
                request.setAttribute("error", "Cập nhật sản phẩm thất bại!");
                request.getRequestDispatcher("/admin/editProduct.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("/admin/editProduct.jsp").forward(request, response);
        }
    }
}
