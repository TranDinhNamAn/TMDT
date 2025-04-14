<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <tr>
              <td class="cart__product__item">
                <img src="../img/shop-cart/karofi.jpg" alt="">
                <div class="cart__product__item__title">
                  <h6>Karofi KSI80</h6>
                </div>
              </td>
              <td class="cart__price">4.800.000 VND</td>
              <td class="cart__quantity">
                <div class="pro-qty">
                  <input type="text" value="1">
                </div>
              </td>
              <td class="cart__total">4.800.000 VND</td>
              <td class="cart__close"><span class="icon_close"></span></td>
            </tr>
            <tr>
              <td class="cart__product__item">
                <img src="../img/shop-cart/sunhouse.jpg" alt="">
                <div class="cart__product__item__title">
                  <h6>Sunhouse SHR76210CK</h6>
                </div>
              </td>
              <td class="cart__price">5.200.000 VND</td>
              <td class="cart__quantity">
                <div class="pro-qty">
                  <input type="text" value="1">
                </div>
              </td>
              <td class="cart__total">5.200.000 VND</td>
              <td class="cart__close"><span class="icon_close"></span></td>
            </tr>
            <tr>
              <td class="cart__product__item">
                <img src="../img/shop-cart/ultima.jpg" alt="">
                <div class="cart__product__item__title">
                  <h6>Pureit Ultima</h6>
                </div>
              </td>
              <td class="cart__price">6.300.000 VND</td>
              <td class="cart__quantity">
                <div class="pro-qty">
                  <input type="text" value="1">
                </div>
              </td>
              <td class="cart__total">6.300.000 VND</td>
              <td class="cart__close"><span class="icon_close"></span></td>
            </tr>
            <tr>
              <td class="cart__product__item">
                <img src="../img/shop-cart/kangaroo.jpg" alt="">
                <div class="cart__product__item__title">
                  <h6>Kangaroo KG50</h6>
                </div>
              </td>
              <td class="cart__price">4.200.000 VNĐ</td>
              <td class="cart__quantity">
                <div class="pro-qty">
                  <input type="text" value="1">
                </div>
              </td>
              <td class="cart__total">4.200.000 VNĐ</td>
              <td class="cart__close"><span class="icon_close"></span></td>
            </tr>
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
</section>
<!-- Shop Cart Section End -->
<jsp:include page="footer.jsp" />
