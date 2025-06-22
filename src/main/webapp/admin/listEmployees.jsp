<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Roles" %>
<%@ page import="DAO.UserDao" %>
<jsp:include page="header.jsp" />

<!-- Table Start -->
<div class="container-fluid pt-4 px-4">
  <div class="col-12">
    <div class="bg-light rounded h-100 p-4">
      <h6 class="mb-4">Danh sách nhân viên</h6>
      <div class="table-responsive">
        <table class="table">
          <thead>
          <tr>
            <th>Tên</th>
            <th>Email</th>
            <th>SĐT</th>
            <th>Tên nhân viên</th>
            <th>Quyền</th>
            <th>Tùy chọn</th>
          </tr>
          </thead>
          <tbody>
          <%
            List<User> employeeList = (List<User>) request.getAttribute("employee");
            if (employeeList != null) {
              for (User employee : employeeList) {
          %>
          <tr>
            <td><%= employee.getName() %></td>
            <td><%= employee.getEmail() %></td>
            <td><%= employee.getPhoneNumber() %></td>
            <td><%= employee.getUserName() %></td>
            <td> <%
              UserDao userDao = new UserDao();
              List<Roles> roles = userDao.getUserRole(employee.getUserID());
              if (roles != null && !roles.isEmpty()) {
            %>
              <%for (int i = 0; i < roles.size(); i++) {%>
              <%= roles.get(i).getRoleName() %><%= (i < roles.size() - 1) ? ", " : "" %>
              <%}%>
              <%
              } else {
              %>
              Chưa phân quyền
              <%
                }
              %>
            </td>
            <td>
              <a href="${pageContext.request.contextPath}/admin/EditEmployeeController?id=<%= employee.getUserID() %>" class="text-warning me-2">
                <i class="fa fa-edit"></i>
              </a>
              <a href="${pageContext.request.contextPath}/admin/DeleteEmployeeController?id=<%= employee.getUserID() %>" class="text-danger"
                 onclick="return confirm('Bạn có chắc chắn muốn xóa nhân viên này không?');">
                <i class="fa fa-trash"></i>
              </a>
            </td>
          </tr>
          <%
            }
          } else {
          %>
          <tr><td colspan="11">Không có nhân viên nào.</td></tr>
          <% } %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- Table End -->

<jsp:include page="footer.jsp" />
