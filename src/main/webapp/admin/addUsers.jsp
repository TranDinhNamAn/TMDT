<%@ page import="Model.Roles" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<!-- Form add User -->
<div class="container-fluid pt-4 px-4">
  <div class="bg-light rounded h-100 p-4">
    <h6 class="mb-4">Thêm Nhân Viên</h6>
    <form action="AddEmployeeController" method="POST">

      <div class="mb-3 row">
        <div class="col-md-6">
          <label for="username" class="form-label">Tên Người Dùng</label>
          <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="col-md-6">
          <label for="fullName" class="form-label">Họ và Tên</label>
          <input type="text" class="form-control" id="fullName" name="fullName" required>
        </div>
      </div>

      <div class="mb-3 row">
        <div class="col-md-6">
          <label for="userEmail" class="form-label">Email</label>
          <input type="email" class="form-control" id="userEmail" name="userEmail" required>
        </div>
        <div class="col-md-6">
          <label for="userPhone" class="form-label">Số Điện Thoại</label>
          <input type="tel" class="form-control" id="userPhone" name="userPhone" required>
        </div>
      </div>

      <div class="mb-3 row">
        <div class="col-md-6">
          <label for="userPassword" class="form-label">Mật Khẩu</label>
          <input type="password" class="form-control" id="userPassword" name="userPassword" required>
        </div>
        <div class="col-md-6">
          <label class="form-label d-block mb-2">Vai Trò</label>
          <%
            List<Roles> allRoles = (List<Roles>) request.getAttribute("allRoles");
            if (allRoles != null) {
              for (Roles role : allRoles) {
          %>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" name="userRole" value="<%= role.getRoleName() %>" id="role_<%= role.getRoleID() %>">
            <label class="form-check-label" for="role_<%= role.getRoleID() %>"><%= role.getRoleName() %></label>
          </div>
          <%
            }
          } else {
          %>
          <p>Không có vai trò nào.</p>
          <%
            }
          %>
        </div>
      </div>
      <!-- Nút Lưu -->
      <div class="text-center">
        <button type="submit" class="btn btn-primary">Lưu lại</button>
      </div>
    </form>
  </div>
</div>
<jsp:include page="../admin/footer.jsp" />



