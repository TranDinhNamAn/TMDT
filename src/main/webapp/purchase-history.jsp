<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Order" %>
<jsp:include page="header.jsp" />

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i> Trang chủ</a>
          <span>Đơn hàng đang</span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<section class="shop-cart spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <h4>Đơn hàng của bạn</h4>
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>Mã đơn</th>
              <th>Ngày đặt</th>
              <th>Người nhận</th>
              <th>SĐT</th>
              <th>Địa chỉ</th>
              <th>Tổng tiền</th>
              <th>Trạng thái</th>
              <th>Chức năng</th>
            </tr>
          </thead>
          <tbody>
          <%
              List<Order> orders = (List<Order>) request.getAttribute("orders");
              if (orders != null && !orders.isEmpty()) {
                  for (Order order : orders) {
                      if (!"Canceled".equalsIgnoreCase(order.getStatus())) {
          %>
              <tr>
                <td><%= order.getOrderID() %></td>
                <td><%= order.getCreateDate() %></td>
                <td><%= order.getFullName() %></td>
                <td><%= order.getPhone() %></td>
                <td><%= order.getShippingAddress() %></td>
               <td>
                 <%
                   Double total = order.getTotalAmount();
                   if (total != null) {
                 %>
                   <%= String.format("%,d VNĐ", total.longValue()) %>
                 <%
                   } else {
                 %>
                   0 VNĐ
                 <%
                   }
                 %>
               </td>
                <td><%= order.getStatus() %></td>
                <td>
                <a class="btn btn-sm btn-primary" href="order-detail?orderID=<%= order.getOrderID() %>">Xem chi tiết</a>
                 <button class="btn btn-danger btn-sm" onclick="cancelOrder(<%= order.getOrderID() %>)">Hủy đơn</button>

                </td>
              </tr>
          <%
                      }
                  }
              } else {
          %>
              <tr><td colspan="8">Không có đơn hàng nào đang chờ xử lý.</td></tr>
          <%
              }
          %>
          </tbody>
        </table>
      </div>
    </div>
  </div>

 <script>
   function cancelOrder(orderID) {
     if (confirm("Bạn có chắc chắn muốn hủy đơn hàng này?")) {
       $.ajax({
         url: "cancel-order",
         method: "POST",
         data: { orderID: orderID },
         success: function(response) {
           alert(response);
           location.reload();
         },
         error: function(xhr, status, error) {
           alert("Hủy đơn hàng thất bại: " + xhr.responseText);
         }
       });
     }
   }
 </script>
</section>
<jsp:include page="footer.jsp" />
