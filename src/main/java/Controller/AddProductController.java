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
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@MultipartConfig
@WebServlet("/admin/AddProductController")
public class AddProductController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ds loai sp
        ProductDAO productDAO = new ProductDAO();
        List<Categories> categories = productDAO.getAllCategories();
        request.setAttribute("categories", categories);
        System.out.println(categories.size());
        request.getRequestDispatcher("/admin/addProducts.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productname = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int stock = Integer.parseInt(request.getParameter("productStock"));
        String category = request.getParameter("productCategoryType");
        Part filePart = request.getPart("productImages");
        String uploadPath = getServletContext().getRealPath("/Image");
        String uploadPath2 = "D:\\CKTMDT1\\src\\main\\webapp\\Image";
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String imageName = UUID.randomUUID().toString() + extension;
        SaveImage saveImage = new SaveImage();
        String savedImageName = saveImage.saveImage(filePart, uploadPath, imageName);
        saveImage.saveImage(filePart, uploadPath2, imageName);

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
            response.sendRedirect(request.getContextPath() + "/admin/GetProductAdminController");
        } else {
            System.out.println("Thêm sp thất bại");
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            request.getRequestDispatcher("/admin/addProducts.jsp").forward(request, response);
        }
    }
}