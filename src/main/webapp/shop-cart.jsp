<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Cart" %>
<jsp:include page="header.jsp" />
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i> Trang chủ</a>
          <span>Giỏ hàng</span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<!-- Shop Cart Section Begin -->
<section class="shop-cart spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="shop__cart__table">
          <table>
            <thead>
            <tr>
              <th>Tên sản phẩm</th>
              <th>Giá</th>
              <th>Số lượng</th>
              <th>Thành tiền</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
              <%
                  List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
                  if (cartItems != null) {
                      for (Cart cartItem : cartItems) {
              %>
              <tr>
                   <td class="cart__product__item">
                    <img src="Image/<%= cartItem.getImage() %>" alt="" style="width: 70px; height: 70px; object-fit: cover;">
                     <div class="cart__product__item__title">
                       <h6><%= cartItem.getNameProduct() %></h6>
                     </div>
                   </td>
                   <td class="cart__price"><%= cartItem.getPrice() %> VNĐ</td>
                  <td class="cart__quantity">
                         <input type="number" value="<%= cartItem.getQuantity() %>" min="1"
                                onchange="updateQuantity(<%= cartItem.getProductID() %>, this.value, this)">
                      </div>
                  </td>
                   <td class="cart__total"><%= cartItem.getPrice() *  cartItem.getQuantity()%> VNĐ</td>
                   <td class="cart__close">
                     <a href="delete-cart-item?productID=<%= cartItem.getProductID() %>">
                       <span class="icon_close"></span>
                     </a>
                   </td>
                 </tr>
              <%
                      }
                  }
              %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="cart__btn">
          <a href="shop.html">Tiếp tục mua hàng</a>
        </div>
      </div>
      <div class="col-lg-6 col-md-6 col-sm-6">
        <div class="cart__btn update__btn">
          <a href="shop-cart.html"><span class="icon_loading"></span> Cập nhật giỏ hàng</a>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6">
      </div>
      <div class="col-lg-4 offset-lg-2">
        <div class="cart__total__procced">
          <h6>Tổng tiền</h6>
          <ul>
            <li>Tổng cộng <span>20.500.000 VNĐ</span></li>
          </ul>
          <a href="checkout.html" class="primary-btn">Tiến hành thanh toán</a>
        </div>
      </div>
    </div>
  </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
  function updateQuantity(productID, quantity, inputElement) {
  console.log("Updating productID:", productID, "with quantity:", quantity);
      $.ajax({
          url: 'update-cart-item',
          type: 'POST',
          data: {
              productID: productID,
              quantity: quantity
          },
          success: function(response) {
          console.log("Response từ server:", response);
              if (response.error) {
                  alert('Lỗi khi cập nhật giỏ hàng!');
                  return;
              }

              // Cập nhật tổng tiền từng sản phẩm
              $(inputElement).closest('tr').find('.cart__total').text(response.total.toLocaleString() + ' VNĐ');

              // (Tùy chọn) cập nhật số lượng nếu backend đã điều chỉnh (giới hạn)
              $(inputElement).val(response.quantity);

              // Cập nhật lại tổng toàn bộ giỏ hàng
              updateCartTotal();
          },
          error: function(xhr, status, error) {
              console.error('Lỗi khi cập nhật:', error);
              alert('Đã có lỗi xảy ra!');
          }
      });
  }
  function updateCartTotal() {
      let total = 0;
      $('.cart__total').each(function () {
          const raw = $(this).text().replace(/[^\d]/g, '');
          total += parseInt(raw) || 0;
      });
      $('.cart__total__procced span').text(total.toLocaleString() + ' VNĐ');
  }
</script>
</section>
<jsp:include page="footer.jsp" />
