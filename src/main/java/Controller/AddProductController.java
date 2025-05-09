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
@WebServlet("/admin/AddProductController")
public class AddProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=== Bắt đầu xử lý đăng ký ===");

        String productname = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int stock = Integer.parseInt(request.getParameter("productStock"));
        int category = Integer.parseInt(request.getParameter("productCategoryType"));
        // Lấy ảnh từ form
        Part filePart = request.getPart("productImages");
        String projectPath = System.getProperty("user.dir"); // thư mục gốc project
        String uploadPath = projectPath + "/src/main/webapp/Image";

        SaveImage saveImage = new SaveImage();
        String savedImageName = saveImage.saveImage(filePart, uploadPath);

        System.out.println("ten: " + productname);
        System.out.println("mota: " + description);
        System.out.println("gia: " + price);
        System.out.println("ton kho: " + stock);
        System.out.println("loai sp: " + category);
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product(
                productname,
                description,
                price,
                stock,
                category
        );

        // Gọi registerUser
        boolean isAdded = productDAO.addProduct(product,savedImageName);
        if (isAdded) {
            System.out.println("Thêm sp thành công.");
            System.out.println("Upload Path: "+ savedImageName);
            response.sendRedirect(request.getContextPath() + "/admin/addProducts.jsp");
        } else {
            System.out.println("Thêm sp thất bại");
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            request.getRequestDispatcher("/admin/addProducts.jsp").forward(request, response);
        }
    }
}