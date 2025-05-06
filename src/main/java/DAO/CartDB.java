package DAO;

import Database.DatabaseConnection;
import Model.Cart;
import org.jdbi.v3.core.Jdbi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDB {
    private final Jdbi jdbi;

    public CartDB() {
        this.jdbi = DatabaseConnection.getJdbi();
    }

    public List<Cart> getCartByUserID(int userID) {
        String sql = "SELECT p.ProductID, p.NameProduct, img.imgURL, p.Description, p.Price, c.Quantity " +
                "FROM cart_items c " +
                "JOIN products p ON c.ProductID = p.ProductID " +
                "JOIN imgproducts img ON p.ProductID = img.ProductID " +
                "WHERE c.UserID = :userID";

        try {
            return jdbi.withHandle(handle ->
                    handle.createQuery(sql)
                            .bind("userID", userID)
                            .map((rs, ctx) -> new Cart(
                                    rs.getInt("ProductID"),
                                    rs.getString("NameProduct"),
                                    rs.getString("imgURL"),
                                    rs.getString("Description"),
                                    rs.getDouble("Price"),
                                    rs.getInt("Quantity")
                            )).list()
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy cart: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean insertCartItem(int userID, int productID, int quantity, Date date) {
        String sql = "INSERT INTO cart_items (UserID, ProductID, Quantity, Created_at) " +
                "VALUES (:userID, :productID, :quantity, :date)";

        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate(sql)
                            .bind("userID", userID)
                            .bind("productID", productID)
                            .bind("quantity", quantity)
                            .bind("date", date)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm sản phẩm vào cart: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCartItem(int userID, int productID) {
        String sql = "DELETE FROM cart_items WHERE UserID = :userID AND ProductID = :productID";

        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate(sql)
                            .bind("userID", userID)
                            .bind("productID", productID)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá sản phẩm khỏi cart: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCartByUserID(int userID) {
        String sql = "DELETE FROM cart_items WHERE UserID = :userID";

        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate(sql)
                            .bind("userID", userID)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá giỏ hàng của user: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCartItemQuantity(int userID, int productID, int quantity) {
        String sql = "UPDATE cart_items SET Quantity = :quantity WHERE UserID = :userID AND ProductID = :productID";

        try {
            return jdbi.withHandle(handle ->
                    handle.createUpdate(sql)
                            .bind("userID", userID)
                            .bind("productID", productID)
                            .bind("quantity", quantity)
                            .execute() > 0
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật số lượng sản phẩm trong giỏ hàng: " + e.getMessage());
            return false;
        }
    }

    public Cart getCartItemByUserAndProduct(int userID, int productID) {
        String sql = "SELECT p.ProductID, p.NameProduct, img.imgURL, p.Description, p.Price, c.Quantity " +
                "FROM cart_items c " +
                "JOIN products p ON c.ProductID = p.ProductID " +
                "JOIN imgproducts img ON p.ProductID = img.ProductID " +
                "WHERE c.UserID = :userID AND c.ProductID = :productID";

        try {
            return jdbi.withHandle(handle ->
                    handle.createQuery(sql)
                            .bind("userID", userID)
                            .bind("productID", productID)
                            .map((rs, ctx) -> new Cart(
                                    rs.getInt("ProductID"),
                                    rs.getString("NameProduct"),
                                    rs.getString("imgURL"),
                                    rs.getString("Description"),
                                    rs.getDouble("Price"),
                                    rs.getInt("Quantity")
                            ))
                            .findOne()
                            .orElse(null)
            );
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy CartItem: " + e.getMessage());
            return null; // Trả về null nếu gặp lỗi
        }
    }


    public static void main(String[] args) throws SQLException {
        CartDB cartDB = new CartDB();
//        System.out.println(cartDB.getCartByUserID(4));
//        cartDB.insertCartItem(4, 3, 2,new Date());
//        cartDB.deleteCartItem(4,3);
//        cartDB.updateCartItemQuantity(4,1,4);
        cartDB.getCartItemByUserAndProduct(4, 1);
    }
}
