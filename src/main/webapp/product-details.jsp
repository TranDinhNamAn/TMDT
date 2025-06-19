<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Product" %>
<%@ page import="java.sql.*" %>
<jsp:include page="header.jsp" />
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i> Trang chủ</a>
          <a href="#">Chi tiết sản phẩm</a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<%
    Product product = (Product) request.getAttribute("product");
%>
<!-- Product Details Section Begin -->
<section class="product-details spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-6">
        <div class="product__details__pic">
          <img src="Image/<%= product.getImage() %>" alt="<%= product.getNameProduct() %>" class="product__big__img" style="width:100%; height:400px; object-fit:cover;">
        </div>
      </div>
      <div class="col-lg-6">
        <div class="product__details__text">
          <h3><%= product.getNameProduct() %></h3>
          <div class="product__details__price"><%= product.getPrice() %> đ</div>
          <p><%= product.getDescription() %></p>
             <input type="hidden" name="id" value="<%= product.getProductID() %>">
            <button id="add-to-cart-btn" class="btn btn-primary" data-productid="<%= product.getProductID() %>">
                Thêm vào giỏ hàng
            </button>

           <button>
               <a class="add-fav" data-productid="<%= product.getProductID() %>">
                 <span class="icon_heart_alt"></span>
               </a>
           </button>
        </div>
      </div>
      <div class="col-lg-12">
        <div class="product__details__tab">
          <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
              <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Mô tả</a>
              <p>Mô tả cho sản phẩm này.</p>
            </li>
            <li class="nav-item">
              <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Đánh giá </a>
            </li>
          </ul>
          <div class="tab-content">
            <div class="tab-pane active" id="tabs-1" role="tabpanel">
              <p></p>
            </div>
            <div class="tab-pane" id="tabs-2" role="tabpanel">
             <p>Chưa có đánh giá nào cho sản phẩm này.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
<script src ="js/add_fav.js"></script>
<script>
document.getElementById("add-to-cart-btn").addEventListener("click", function() {
    const productId = this.getAttribute("data-productid");

    fetch("add-to-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "productId=" + encodeURIComponent(productId)
    })
    .then(response => response.text())
    .then(message => {
        alert(message);
    })
    .catch(error => {
        alert("Đã xảy ra lỗi khi thêm sản phẩm vào giỏ hàng.");
        console.error("Lỗi:", error);
    });
});
</script>
</section>
<!-- Product Details Section End -->
<jsp:include page="footer.jsp" />
