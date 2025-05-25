<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Address" %>
<jsp:include page="header.jsp" />

<!-- Table Start -->
<div class="container-fluid pt-4 px-4">
  <div class="col-12">
    <div class="bg-light rounded h-100 p-4">
      <h6 class="mb-4">Danh sách người dùng</h6>
      <div class="table-responsive">
        <table class="table">
          <thead>
          <tr>
            <th>Tên</th>
            <th>Email</th>
            <th>SĐT</th>
            <th>Tên người dùng</th>
            <th>Địa chỉ</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Tùy chọn</th>
          </tr>
          </thead>
          <tbody>
          <%
            List<User> userList = (List<User>) request.getAttribute("user");
            if (userList != null) {
              for (User user : userList) {
          %>
          <tr>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getPhoneNumber() %></td>
            <td><%= user.getUserName() %></td>
            <td> <%
              List<Address> addresses = user.getAddress();
              if (addresses != null && !addresses.isEmpty()) {
            %>
              <%= addresses.get(0) %> <!-- hoặc hiển thị cụ thể từng phần -->
              <%
              } else {
              %>
              Không có địa chỉ
              <%
                }
              %>
            </td>
            <td><%= user.getCreateDate() %></td>
            <td><%= user.getLastUpdateDate() %></td>
            <td>
              <a href="editUsers.jsp?id=<%= user.getUserID() %>" class="text-warning me-2">
                <i class="fa fa-edit"></i>
              </a>
              <a href="deleteUsers?id=<%= user.getUserID() %>" class="text-danger">
                <i class="fa fa-trash"></i>
              </a>
            </td>
          </tr>
          <%
            }
          } else {
          %>
          <tr><td colspan="11">Không có người dùng nào.</td></tr>
          <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- Table End -->

<jsp:include page="footer.jsp" />
