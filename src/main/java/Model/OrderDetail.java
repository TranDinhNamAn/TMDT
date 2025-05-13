package Model;

import java.util.Date;

public class OrderDetail {
    private int OrderDetailID;
    private int OrderID;
    private int ProductID;
    private int Quantity;
    private Double Price;
    private Date DateAdd;

    public OrderDetail(int productID, int quantity, Double price, Date dateAdd) {
        ProductID = productID;
        Quantity = quantity;
        Price = price;
        DateAdd = dateAdd;
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
