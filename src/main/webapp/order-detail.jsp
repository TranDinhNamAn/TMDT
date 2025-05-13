<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.OrderDetail" %>
<jsp:include page="header.jsp" />

<div class="container mt-5">
  <h4>Chi tiết đơn hàng #<%= request.getAttribute("orderID") %></h4>
  <table class="table table-bordered mt-3">
    <thead>
      <tr>
        <th>Sản phẩm</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Thành tiền</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<OrderDetail> details = (List<OrderDetail>) request.getAttribute("details");
        if (details != null && !details.isEmpty()) {
            for (OrderDetail item : details) {
      %>
        <tr>
          <td><%= item.getNameProduct() %></td>
          <td><%= String.format("%,d VNĐ", item.getPrice().longValue()) %></td>
          <td><%= item.getQuantity() %></td>
          <td><%= String.format("%,d VNĐ", (long)(item.getPrice() * item.getQuantity())) %></td>
        </tr>
      <%
            }
        } else {
      %>
        <tr><td colspan="4">Không có chi tiết đơn hàng.</td></tr>
      <%
        }
      %>
    </tbody>
  </table>
  <a href="Order" class="btn btn-secondary" style="margin-bottom: 20px;">Quay lại danh sách đơn</a>
</div>
<jsp:include page="footer.jsp" />
