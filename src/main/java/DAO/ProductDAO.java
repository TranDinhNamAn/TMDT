package DAO;

import Database.DatabaseConnection;
import Model.Product;
import Model.Categories;
import Utils.SaveImage;
import org.jdbi.v3.core.Jdbi;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ProductDAO {
    private final Jdbi jdbi;

    public ProductDAO() {
        this.jdbi = DatabaseConnection.getJdbi();
    }

    //lấy ds sp
    public List<Product> getAllProduct() {
        String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
                "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
                "FROM products p " +
                "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID";


        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }
    //lay loai sp
    public List<Categories> getAllCategories() {
        String sql = "SELECT * FROM categories";


        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Categories.class)
                        .list()
        );
    }
    //lay sp theo Id
    public Product getProductById(int id) {
        String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
                "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
                "FROM products p " +
                "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID " +
                "WHERE p.ProductID = :id";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public boolean addProduct(Product product, String image) {
        try {
            return jdbi.withHandle(handle -> {
                // Chèn sản phẩm và lấy ProductID vừa thêm
                Long productId = handle.createUpdate("INSERT INTO products (NameProduct, Description, Price, Stock, CategoriesID, CreateDate, LastUpdateDate) " +
                                "VALUES (:productName, :productDescription, :productPrice, :productStock, :productCategoryType, :createDate, :lastUpdateDate)")
                        .bind("productName", product.getNameProduct())
                        .bind("productDescription", product.getDescription() != null ? product.getDescription() : "")
                        .bind("productPrice", product.getPrice())
                        .bind("productStock", product.getStock())
                        .bind("productCategoryType", product.getCategories())
                        .bind("createDate", product.getCreateDate())
                        .bind("lastUpdateDate", product.getLastUpdateDate())
                        .executeAndReturnGeneratedKeys("ProductID")
                        .mapTo(Long.class)
                        .one();

                // Chèn ảnh vào bảng imgproducts
                int rows = handle.createUpdate("INSERT INTO imgproducts (ProductID, AltText, LastUpdateDate, imgURL) " +
                                "VALUES (:productId, :altText, :lastUpdateDate, :imgUrl)")
                        .bind("productId", productId)
                        .bind("altText", product.getNameProduct())
                        .bind("lastUpdateDate", new java.sql.Timestamp(System.currentTimeMillis()))
                        .bind("imgUrl", image != null ? image : "default_product.png")
                        .execute();
                return rows > 0;
            });
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            return false;
        }
    }
    //sửa sp
    public boolean editProduct(Product product, String image) {
        try {
            return jdbi.withHandle(handle -> {
                // Cập nhật sản phẩm
                int productUpdated = handle.createUpdate(
                                "UPDATE products SET " +
                                        "NameProduct = :productName, " +
                                        "Description = :productDescription, " +
                                        "Price = :productPrice, " +
                                        "Stock = :productStock, " +
                                        "CategoriesID = :productCategoryType, " +
                                        "LastUpdateDate = :lastUpdateDate " +
                                        "WHERE ProductID = :productId")
                        .bind("productId", product.getProductID())
                        .bind("productName", product.getNameProduct())
                        .bind("productDescription", product.getDescription() != null ? product.getDescription() : "")
                        .bind("productPrice", product.getPrice())
                        .bind("productStock", product.getStock())
                        .bind("productCategoryType", product.getCategories())
                        .bind("lastUpdateDate", new java.sql.Timestamp(System.currentTimeMillis()))
                        .execute();

                // Nếu có ảnh mới, cập nhật ảnh
                if (image != null && !image.isEmpty()) {
                    handle.createUpdate(
                                    "UPDATE imgproducts SET " +
                                            "imgURL = :imgUrl, " +
                                            "AltText = :altText, " +
                                            "LastUpdateDate = :lastUpdateDate " +
                                            "WHERE ProductID = :productId")
                            .bind("productId", product.getProductID())
                            .bind("imgUrl", image)
                            .bind("altText", product.getNameProduct())
                            .bind("lastUpdateDate", new java.sql.Timestamp(System.currentTimeMillis()))
                            .execute();
                }

                return productUpdated > 0;
            });
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
            return false;
        }
    }

    // Xóa sp theo id
    public boolean deleteProduct(int productId) {
        try {
            return jdbi.withHandle(handle -> {
                // Xóa bản ghi ảnh trước
                handle.createUpdate("DELETE FROM imgproducts WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute();

                // Sau đó xóa sản phẩm
                return handle.createUpdate("DELETE FROM products WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute() > 0;
            });
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct2(int productId) {
        try {
            return jdbi.withHandle(handle -> {
                // Lấy tên file ảnh từ bảng imgproducts
                String imageName = handle.createQuery("SELECT imgURL FROM imgproducts WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null);

                // Xóa bản ghi ảnh trước (nếu cần)
                handle.createUpdate("DELETE FROM imgproducts WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute();

                // Xóa sản phẩm
                return handle.createUpdate("DELETE FROM products WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute() > 0;
            });
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        SaveImage saveImage = new SaveImage();
     //   productDAO.addProduct(new Product("a", "a", 1, 100, 1),image);
//        productDAO.deleteProduct(123);
        //productDAO.editProduct(new Product(122, "asd", "asd", 112, 1,3 ),"");
        List<Product> p = productDAO.getAllProduct();
        System.out.println(p.size());
    }
}

