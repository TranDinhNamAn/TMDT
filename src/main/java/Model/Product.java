package Model;

import java.util.Date;

public class Product {
    private int ProductID;
    private Categories CategoriesID;
    private String NameProduct;
    private String Description;
    private String Image;
    private int Price;
    private int Stock;
    private Date CreateDate;
    private Date LastUpdateDate;

    public Product(int productID, Categories categoriesID, String nameProduct, String description, String image, int price, int stock, Date createDate, Date lastUpdateDate) {
        ProductID = productID;
        CategoriesID = categoriesID;
        NameProduct = nameProduct;
        Description = description;
        Image = image;
        Price = price;
        Stock = stock;
        CreateDate = createDate;
        LastUpdateDate = lastUpdateDate;
    }

    public Categories getCategoriesID() {
        return CategoriesID;
    }

    public void setCategoriesID(Categories categoriesID) {
        CategoriesID = categoriesID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getLastUpdateDate() {
        return LastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }
}
