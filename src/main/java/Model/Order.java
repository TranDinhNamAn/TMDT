package Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private int OrderID;
    private int UserID;
    private String FullName;
    private int Phone;
    private String Status;
    private String CustomerNote;
    private Date CreateDate;
    private String ShippingAddress;
    private String PaymentMethod;
    private Double TotalAmount;
    private List<OrderDetail> listOrderDetail;

    public Order(int userID,String fullName,int phone, String status, String customerNote, Date createDate, String shippingAddress, String paymentMethod, Double totalAmount, List<OrderDetail> listOrderDetail) {
        UserID = userID;
        FullName = fullName;
        Phone = phone;
        Status = status;
        CustomerNote = customerNote;
        CreateDate = createDate;
        ShippingAddress = shippingAddress;
        PaymentMethod = paymentMethod;
        TotalAmount = totalAmount;
        this.listOrderDetail = listOrderDetail;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCustomerNote() {
        return CustomerNote;
    }

    public void setCustomerNote(String customerNote) {
        CustomerNote = customerNote;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }
}
