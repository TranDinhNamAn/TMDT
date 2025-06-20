<%@ page import="Model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<!-- Table Start -->
<div class="container-fluid pt-4 px-4">
  <div class="col-12">
    <div class="bg-light rounded h-100 p-4">
      <h6 class="mb-4">Danh sách đơn hàng</h6>
      <div class="table-responsive">
        <table class="table">
          <thead>
          <tr>
            <th scope="col">Mã đơn hàng</th>
            <th scope="col">Tên khách hàng</th>
            <th scope="col">Ngày đặt hàng</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Trạng thái đơn hàng</th>
            <th scope="col">Ghi chú</th>
            <th scope="col">Thành tiền</th>
            <th scope="col">Tùy chọn</th>
          </tr>
          </thead>
          <tbody>
          <%
            List<Order> orders = (List<Model.Order>) request.getAttribute("orders");
            if (orders != null) {
              for (Model.Order order : orders) {
          %>
          <tr>
            <th scope="row"><%= order.getOrderID() %></th>
            <td><%= order.getFullName() %></td>
            <%
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              String formattedDate = sdf.format(order.getCreateDate());
            %>
            <td><%= formattedDate %></td>
            <td><%= order.getShippingAddress() %></td>
            <form action="${pageContext.request.contextPath}/admin/update-order" method="post" class="d-flex align-items-center">
              <input type="hidden" name="orderId" value="<%= order.getOrderID() %>">
            <td>
                <!-- Dropdown chọn trạng thái -->
                <select name="status" class="form-select form-select-sm me-2">
                  <option value=""><%=order.getStatus()%></option>
                  <option value="Pending">Pending</option>
                  <option value="Shipping">Shipping</option>
                  <option value="Delivered">Delivered</option>
                  <option value="Cancelled">Cancelled</option>
                </select>
            </td>
            <td><%= order.getCustomerNote() %></td>
            <td><%= order.getTotalAmount() %> VNĐ</td>
              <td class="text-center">
                <button type="submit"
                        class="btn btn-warning btn-sm d-block mx-auto"
                        title="Lưu trạng thái"
                        onclick="return confirm('Bạn có muốn cập nhật đơn hàng này không?');">
                  <i class="fa fa-save"></i>
                </button>
              </td>
            </form>
          </tr>
          <%
            }
          } else {
          %>
          <tr>
            <td colspan="8" class="text-center">Không có đơn hàng nào</td>
          </tr>
          <% } %>
          </tbody>

        </table>
      </div>
    </div>
  </div>
</div>
<!-- Table End -->
<jsp:include page="/admin/footer.jsp" />


