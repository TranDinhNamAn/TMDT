package DAO;

import Database.DatabaseConnection;
import Model.Product;
import Model.Categories;
import Utils.SaveImage;
import org.jdbi.v3.core.Jdbi;
import java.io.File;
import java.sql.Timestamp;
import java.util.Collections;
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
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID" +
                " ORDER BY p.CreateDate DESC";


        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(Product.class)
                        .list()
        );
    }
    public List<Product> getProductsByCategory(int categoryId) {
        String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
                "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
                "FROM products p " +
                "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID " +
                "WHERE p.CategoriesID = :categoryId";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("categoryId", categoryId)
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
    public List<Product> getProductsByName(String name) {
        String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
                "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
                "FROM products p " +
                "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID " +
                "WHERE p.NameProduct LIKE :name";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("name", "%" + name + "%")
                        .mapToBean(Product.class)
                        .list()
        );
    }
    public List<Product> getProductsByPriceRange(int priceType) {
        String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
                "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
                "FROM products p " +
                "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
                "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID ";
        switch (priceType) {
            case 1:
                sql += "WHERE p.Price < 200000";
                break;
            case 2:
                sql += "WHERE p.Price >= 200000 AND p.Price <= 350000";
                break;
            case 3:
                sql += "WHERE p.Price > 350000";
                break;
            default:
                return Collections.emptyList();
        }
        String finalSql = sql;
        return jdbi.withHandle(handle ->
                handle.createQuery(finalSql)
                        .mapToBean(Product.class)
                        .list()
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
                handle.createUpdate("DELETE FROM imgproducts WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute();

                return handle.createUpdate("DELETE FROM products WHERE ProductID = :productID")
                        .bind("productID", productId)
                        .execute() > 0;
            });
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
            return false;
        }
    }
//tìm kiếm sp
public List<Product> searchProductsByName(String keyword) {
    String sql = "SELECT p.ProductID, c.Name AS Categories, p.NameProduct, p.Description, " +
            "i.imgURL AS Image, p.Price, p.Stock, p.CreateDate, p.LastUpdateDate " +
            "FROM products p " +
            "LEFT JOIN imgproducts i ON p.ProductID = i.ProductID " +
            "LEFT JOIN categories c ON p.CategoriesID = c.CategoriesID " +
            "WHERE p.NameProduct LIKE :keyword " +
            "ORDER BY p.CreateDate DESC";

    return jdbi.withHandle(handle ->
            handle.createQuery(sql)
                    .bind("keyword", "%" + keyword + "%")
                    .mapToBean(Product.class)
                    .list()
    );
}
}

