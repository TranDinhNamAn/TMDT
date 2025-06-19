<%@ page import="Model.Roles" %>
<%@ page import="Model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<%
    User employee = (User) request.getAttribute("employee");
    List<Roles> allRoles = (List<Roles>) request.getAttribute("allRoles");
    List<Roles> userRoles = (List<Roles>) request.getAttribute("userRoles");
%>

<!-- Form edit User -->
<div class="container-fluid pt-4 px-4">
    <div class="bg-light rounded h-100 p-4">
        <h6 class="mb-4">Chỉnh sửa Nhân Viên</h6>
        <form action="EditEmployeeController" method="POST">
            <input type="hidden" name="userId" value="<%= employee.getUserID() %>">

            <div class="mb-3 row">
                <div class="col-md-6">
                    <label for="username" class="form-label">Tên Người Dùng</label>
                    <input type="text" class="form-control" id="username" name="username"
                           value="<%= employee.getUserName() %>" required>
                </div>
                <div class="col-md-6">
                    <label for="fullName" class="form-label">Họ và Tên</label>
                    <input type="text" class="form-control" id="fullName" name="fullName"
                           value="<%= employee.getName() %>" required>
                </div>
            </div>

            <div class="mb-3 row">
                <div class="col-md-6">
                    <label for="userEmail" class="form-label">Email</label>
                    <input type="email" class="form-control" id="userEmail" name="userEmail"
                           value="<%= employee.getEmail() %>" required>
                </div>
                <div class="col-md-6">
                    <label for="userPhone" class="form-label">Số Điện Thoại</label>
                    <input type="tel" class="form-control" id="userPhone" name="userPhone"
                           value="<%= employee.getPhoneNumber() %>" required>
                </div>
            </div>

            <div class="mb-3 row">
                <div class="col-md-6">
                    <label class="form-label d-block mb-2">Vai Trò</label>
                    <%
                        if (allRoles != null) {
                            for (Roles role : allRoles) {
                                boolean checked = false;
                                if (userRoles != null) {
                                    for (Roles r : userRoles) {
                                        if (r.getRoleID() == role.getRoleID()) {
                                            checked = true;
                                            break;
                                        }
                                    }
                                }
                    %>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" name="userRole"
                               value="<%= role.getRoleName() %>" id="role_<%= role.getRoleID() %>"
                            <%= checked ? "checked" : "" %> >
                        <label class="form-check-label" for="role_<%= role.getRoleID() %>">
                            <%= role.getRoleName() %>
                        </label>
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
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../admin/footer.jsp" />
