blog-details-2.jsp<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Cart" %>
<jsp:include page="header.jsp" />
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i>Trang chủ</a>
          <span>Thanh toán</span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
  <div class="container">
    <form action="${pageContext.request.contextPath}/payment" method="post" class="checkout__form">
      <div class="row">
        <div class="col-lg-8">
          <h5>Chi tiết hóa đơn</h5>
          <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="checkout__form__input">
                <p>Họ và tên<span>*</span></p>
                <input type="text" name="fullName">
              </div>
            </div>

            <div class="col-lg-12">
              <div class="checkout__form__input">
                <p>Địa chỉ <span>*</span></p>
                <input type="text" placeholder="Số nhà, tên đường, xã, huyện, tỉnh (chỉ giao hàng nội thành TPHCM)" name="address">
              </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="checkout__form__input">
                <p>Điện thoại <span>*</span></p>
                <input type="text" name="phone">
              </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
              <div class="checkout__form__input">
                <p>Email <span>*</span></p>
                <input type="text" name="email">
              </div>
            </div>
            <div class="col-lg-12">
              <div class="checkout__form__input">
                <p>Ghi chú <span>*</span></p>
                <input type="text"
                       placeholder="Lưu ý về đơn đặt hàng của bạn, ví dụ: giao giờ hành chính" name="note">
              </div>
            </div>
            <div class="col-lg-12">
              <div class="checkout__form__input">
                <p>Nhập mã giảm giá (nếu có)<span></span></p>
                <input type="text"
                       placeholder="Nhập mã: SUNDAY123">
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="checkout__order">
            <h5>Đơn đặt hàng của bạn</h5>
            <div class="checkout__order__product">
              <ul>
                <li>
                  <span class="top__text">Sản phẩm</span>
                  <span class="top__text__right">Tổng cộng</span>
                </li>
                 <%
                      List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
                       if (cartItems != null) {
                      int count = 1;
                      for (Cart item : cartItems) {
                    %>
                        <li>
                          <%= String.format("%02d. %s x %d", count++, item.getNameProduct(), item.getQuantity()) %>
                          <span><%= String.format("%,.0fđ", item.getPrice() * item.getQuantity()) %></span>
                        </li>
                    <%
                      }}
                    %>
              </ul>
            </div>
            <div class="checkout__order__total">
              <ul>
                <li>Được giảm <span>200.000đ</span></li>
                <li><li>Tổng cộng <span>
                <%
                        Double totalPrice = (Double) request.getAttribute("totalPrice");
                        out.print(totalPrice != null ? String.format("%,d VNĐ", totalPrice.longValue()) : "0 VNĐ");
                    %>
                </span></li></span></li>
              </ul>
            </div>
            <div class="checkout__order__widget">
              <p>Nếu bạn đã có tài khoản vui lòng đăng nhập trước khi thanh toán.</p>
             <label for="cash">
               Thanh toán tiền mặt
               <input type="radio" name="paymentMethod" value="Cash" id="cash">
               <span class="checkmark"></span>
             </label>
             <label for="bank">
               Chuyển khoản
               <input type="radio" name="paymentMethod" value="Bank Transfer" id="bank">
               <span class="checkmark"></span>
             </label>
            </div>
            <button type="submit" class="site-btn">Đặt hàng</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</section>
<!-- Checkout Section End -->
<jsp:include page="footer.jsp" />
