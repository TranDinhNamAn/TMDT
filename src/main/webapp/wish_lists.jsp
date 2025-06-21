<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Product" %>
<%@ page import="java.util.List" %>

<jsp:include page="header.jsp" />

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i> Trang chủ</a>
          <span>Yêu thích</span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<!-- Shop Section Begin -->
<section class="shop spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-3">
        <!-- Sidebar giữ nguyên -->
      </div>

      <div class="col-lg-9 col-md-9">
        <div class="row">
          <%
              List<Product> favoriteProducts = (List<Product>) request.getAttribute("favoriteProducts");
              if (favoriteProducts != null && !favoriteProducts.isEmpty()) {
                  for (Product p : favoriteProducts) {
          %>
          <div class="col-lg-3 col-md-4 col-sm-6 mix">
              <div class="product__item">
                  <div class="product__item__pic set-bg">
                      <img src="Image/<%= p.getImage() %>" style="width: 100%; height: 270px; object-fit: cover;" alt="<%= p.getNameProduct() %>">
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
                          <li>
                              <a href="shopping-cart"><span class="icon_bag_alt"></span></a>
                          </li>
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
                      <div class="product__price"><%= p.getPrice() %> VNĐ</div>
                  </div>
              </div>
          </div>
          <%
                  }
              } else {
          %>
          <div class="col-12">
              <p>Không có sản phẩm yêu thích nào.</p>
          </div>
          <%
              }
          %>
        </div>
      </div>
    </div>
  </div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function () {
    $('.add-fav').on('click', function (e) {
      e.preventDefault();
      const productId = $(this).data('productid');

      if (confirm("Bạn có muốn xóa sản phẩm này khỏi yêu thích?")) {
        $.ajax({
          url: 'remove-favorite',
          method: 'POST',
          data: {
            productId: productId
          },
          success: function (res) {
            alert("Đã xoá khỏi danh sách yêu thích!");
            location.reload(); // Tải lại trang để cập nhật danh sách
          },
          error: function () {
            alert("Có lỗi xảy ra, vui lòng thử lại!");
          }
        });
      }
    });
  });
</script>
</section>
<!-- Shop Section End -->
<jsp:include page="footer.jsp" />
