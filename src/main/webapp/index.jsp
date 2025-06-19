<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <jsp:include page="header.jsp" />
</head>
<body>

<!-- Banner Section Begin -->
<section class="banner">
    <div class="m-auto">
        <div class="banner__slider owl-carousel">
            <div class="banner__item">
                <a href="shop.jsp">
                    <img src="assets/img/banner/banner-3.webp" alt="Banner 1" class="img-fluid">
                </a>
            </div>
            <div class="banner__item">
                <a href="blog-details-4.jsp">
                    <img src="assets/img/banner/banner-1.webp" alt="Banner 2" class="img-fluid">
                </a>
            </div>
            <div class="banner__item">
                <a href="service.jsp">
                    <img src="assets/img/banner/banner-2.webp" alt="Banner 3" class="img-fluid">
                </a>
            </div>
        </div>
    </div>
</section>
<!-- Banner Section End -->

<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-4">
                <div class="section-title">
                    <h4>Sản phẩm mới</h4>
                </div>
            </div>
            <div class="col-lg-8 col-md-8">
              <div class="row mb-4">
                   <!-- Tìm kiếm sản phẩm -->
                  <div class="col-md-4">
                      <input type="text" id="searchInput" class="form-control" placeholder="Nhập tên sản phẩm...">
                  </div>
                  <!-- Lọc theo loại sản phẩm -->
                  <div class="col-md-4">
                      <select id="categoryFilter" class="form-control">
                          <option value="*">Lọc theo loại</option>
                          <option value="1">Dây sạc</option>
                          <option value="2">Tai nghe</option>
                          <option value="3">Hộp đựng tai nghe</option>
                          <option value="4">Hộp đựng tay nghe airpod</option>
                          <option value="5">Popsocket</option>
                          <option value="6">Iring</option>
                      </select>
                  </div>

                  <!-- Lọc theo giá -->
                  <div class="col-md-4">
                      <select id="priceFilter" class="form-control">
                          <option value="*">Lọc theo giá</option>
                          <option value="1">Dưới 200k</option>
                          <option value="2">200k - 350k</option>
                          <option value="3">Trên 350k</option>
                      </select>
                  </div>
              </div>
            </div>
        </div>
        <div class="row property__gallery">
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
            %>
                <%
                    for (Product p : products) {
                %>
                <div class="col-lg-3 col-md-4 col-sm-6 mix ">
                    <div class="product__item">
                        <div class="product__item__pic set-bg">
                        <img src="Image/<%= p.getImage() %>" style="width: 100%; height: 270px; object-fit: cover;">
                            <ul class="product__hover">
                                <li>
                                <a href="Image/<%= p.getImage() %>" class="image-popup">
                                    <img src="Image/<%= p.getImage() %>" alt="Ảnh sản phẩm" style="width: 70px; height: 70px; object-fit: cover;">
                                </a>
                                </li>
                              <li>
                                <a class="add-fav" data-productid="<%= p.getProductID() %>">
                                  <span class="icon_heart_alt"></span>
                                </a>
                              </li>
                                <li><a href="shopping-cart"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="product-detail?id=<%= p.getProductID() %>"><%= p.getNameProduct() %></a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price"><%= p.getPrice() %></div>
                        </div>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <div class="col-12">
                    <p>Hiện không có sản phẩm nào để hiển thị.</p>
                </div>
                <%
                }
            %>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Services Section Begin -->
<section class="services spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-car"></i>
                    <h6>Vận chuyển nội thành</h6>
                    <p>Miễn phí vận chuyển</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-money"></i>
                    <h6>Đảm bảo hoàn tiền</h6>
                    <p>Nếu có lỗi sản phẩm</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-support"></i>
                    <h6>Hỗ trợ trực tuyến 24/7</h6>
                    <p>Hỗ trợ tận tình</p>
                </div>
            </div>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="services__item">
                    <i class="fa fa-headphones"></i>
                    <h6>Thanh toán an toàn</h6>
                    <p>Thanh toán an toàn 100%</p>
                </div>
            </div>
        </div>
    </div>
<script src ="js/add_fav.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function () {
const searchInput = document.getElementById("searchInput");
const categoryFilter = document.getElementById("categoryFilter");
const priceFilter = document.getElementById("priceFilter");
const productContainer = document.querySelector(".property__gallery");

searchInput.addEventListener("input", filterProducts);
categoryFilter.addEventListener("change", filterProducts);
priceFilter.addEventListener("change", filterProducts);

function filterProducts() {
    const name = searchInput.value.trim();
    const category = categoryFilter.value !== "*" ? categoryFilter.value : "";
    const price = priceFilter.value !== "*" ? priceFilter.value : "";

    const formData = new URLSearchParams();
    formData.append("searchInput", name);
    formData.append("categoryFilter", category);
    formData.append("priceFilter", price);

    fetch("filter-list", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: formData
    })
    .then(response => response.text())
    .then(html => {
        productContainer.innerHTML = html;
    })
    .catch(error => {
        console.error("Lỗi khi tải sản phẩm:", error);
    });
}
});
</script>
</section>
<!-- Services Section End -->
<jsp:include page="footer.jsp" />
</body>
</html>
