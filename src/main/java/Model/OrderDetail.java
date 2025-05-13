package Model;

import java.util.Date;

public class OrderDetail {
    private int OrderDetailID;
    private int OrderID;
    private int ProductID;
    private String NameProduct;
    private int Quantity;
    private Double Price;
    private Date DateAdd;

    public OrderDetail(int productID, int quantity, Double price, Date dateAdd) {
        ProductID = productID;
        Quantity = quantity;
        Price = price;
        DateAdd = dateAdd;
    }

    public OrderDetail(int orderID, String nameProduct, int quantity, Double price, Date dateAdd) {
        OrderID = orderID;
        NameProduct = nameProduct;
        Quantity = quantity;
        Price = price;
        DateAdd = dateAdd;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Date getDateAdd() {
        return DateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        DateAdd = dateAdd;
    }
}
