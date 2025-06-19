<%@ page import="Model.User" %>
<%@ page import="DAO.UserDao" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Roles" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <head>
    <meta charset="utf-8">
    <title>Lọc Xanh - Admin</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="${pageContext.request.contextPath}/assets/admin/img/favicon.icon" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/admin/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/admin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/admin/css/style.css" rel="stylesheet">
  </head>

  <body>
  <div class="">
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
      <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
        <span class="sr-only">Loading/assets.</span>
      </div>
    </div>
      <%
            User user = (User) session.getAttribute("user");
            UserDao userDao = new UserDao();
              List<Roles> roles = (List<Roles>) session.getAttribute("role");
            String avatar = user != null ? user.getProfilePicture() : "https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain";
            String username = user != null ? user.getUserName() : "Khách";
          %>
    <!-- Spinner End -->
    <div class="sidebar pe-4 pb-3">
      <nav class="navbar bg-light navbar-light">
        <a href="${pageContext.request.contextPath}/admin/indexAdmin.jsp" class="navbar-brand mx-4 mb-3">
          <h3 class="text-primary">Lọc Xanh</h3>
        </a>
        <% if (user != null) {%>
        <div class="dropdown user-menu">
            <img class="rounded-circle me-lg-2" src="<%= avatar %>" alt="Avatar" style="width: 40px; height: 40px;">
          <div class="d-none d-lg-inline-flex flex-column">
            <span><%= username.length() > 8 ? username.substring(0, 8) + "..." : username %></span>
            <%= roles.get(0).getRoleName().contains("Employee") ? "Employee" : roles.get(0).getRoleName() %>
          </div>
        </div>

        <%}%>
        <div class="navbar-nav w-100">
          <a href="${pageContext.request.contextPath}/admin/indexAdmin.jsp"
             class="nav-item nav-link ${currentPage.contains('indexAdmin.jsp') ? 'active' : ''}">
            <i class="fa fa-tachometer-alt me-2"></i>Tổng quan
          </a>
          <div class="nav-item dropdown">
            <a href="${pageContext.request.contextPath}/admin/listCategories.jsp"
               class="nav-link dropdown-toggle ${currentPage.contains('listCategories.jsp') || currentPage.contains('addCategories.jsp') ? 'active' : ''}"
               data-bs-toggle="dropdown">
              <i class="fa fa-list me-2"></i>Danh mục
            </a>
            <div class="dropdown-menu bg-transparent border-0">
              <a href="${pageContext.request.contextPath}/admin/listCategories.jsp"
                 class="dropdown-item ${currentPage.contains('listCategories.jsp') ? 'active' : ''}">
                Danh sách danh mục
              </a>
              <a href="${pageContext.request.contextPath}/admin/addCategories.jsp"
                 class="dropdown-item ${currentPage.contains('addCategories.jsp') ? 'active' : ''}">
                Thêm danh mục
              </a>
            </div>
          </div>
          <div class="nav-item dropdown">
            <a href="${pageContext.request.contextPath}/admin/listProducts.jsp"
               class="nav-link dropdown-toggle ${currentPage.contains('listProducts.jsp') || currentPage.contains('addProducts.jsp') ? 'active' : ''}"
               data-bs-toggle="dropdown">
              <i class="fa fa-cube me-2"></i>Sản phẩm
            </a>
            <div class="dropdown-menu bg-transparent border-0">
              <a href="${pageContext.request.contextPath}/admin/GetProductAdminController"
                 class="dropdown-item ${currentPage.contains('listProducts.jsp') ? 'active' : ''}">
                Danh sách sản phẩm
              </a>
              <a href="${pageContext.request.contextPath}/admin/AddProductController"
                 class="dropdown-item ${currentPage.contains('addProducts.jsp') ? 'active' : ''}">
                Thêm sản phẩm
              </a>
            </div>
          </div>
          <div class="nav-item dropdown">
            <a href="${pageContext.request.contextPath}/admin/listUsers.jsp"
               class="nav-link dropdown-toggle ${currentPage.contains('listUsers.jsp') || currentPage.contains('addUsers.jsp') ? 'active' : ''}"
               data-bs-toggle="dropdown">
              <i class="fa fa-users me-2"></i>Người dùng
            </a>
            <div class="dropdown-menu bg-transparent border-0">
              <a href="${pageContext.request.contextPath}/admin/GetAllUserController"
                 class="dropdown-item ${currentPage.contains('listUsers.jsp') ? 'active' : ''}">
                Danh sách người dùng
              </a>
              <a href="${pageContext.request.contextPath}/admin/GetAllEmployeeController"
                 class="dropdown-item ${currentPage.contains('listEmployees.jsp') ? 'active' : ''}">
                Danh sách nhân viên
              </a>
              <a href="${pageContext.request.contextPath}/admin/AddEmployeeController"
                 class="dropdown-item ${currentPage.contains('addUsers.jsp') ? 'active' : ''}">
                Thêm nhân viên
              </a>
            </div>
          </div>
          <div class="nav-item dropdown">
            <a href="${pageContext.request.contextPath}/admin/listOrders.jsp"
               class="nav-link dropdown-toggle ${currentPage.contains('listOrders.jsp') || currentPage.contains('addOrders.jsp') ? 'active' : ''}"
               data-bs-toggle="dropdown">
              <i class="fa fa-shopping-cart me-2"></i>Đơn hàng
            </a>
            <div class="dropdown-menu bg-transparent border-0">
              <a href="${pageContext.request.contextPath}/admin/listOrders.jsp"
                 class="dropdown-item ${currentPage.contains('listOrders.jsp') ? 'active' : ''}">
                Danh sách đơn hàng
              </a>
              <a href="${pageContext.request.contextPath}/admin/addOrders.jsp"
                 class="dropdown-item ${currentPage.contains('addOrders.jsp') ? 'active' : ''}">
                Thêm đơn hàng
              </a>
            </div>
          </div>
          <a href="${pageContext.request.contextPath}/admin/listContact.jsp"
             class="nav-item nav-link ${currentPage.contains('listContact.jsp') ? 'active' : ''}">
            <i class="fa fa-envelope me-2"></i>Phản hồi
          </a>
          <a href="${pageContext.request.contextPath}/index.jsp"
             class="nav-item nav-link ${currentPage.contains('index.jsp') ? 'active' : ''}">
            <i class="fa fa-arrow-left me-2"></i>Về trang chủ
          </a>
        </div>
      </nav>
    </div>
    <!-- Content Start -->
    <div class="content">
      <!-- Navbar Start -->
      <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
        <a href="indexAdmin.jsp" class="navbar-brand d-flex d-lg-none me-4">
          <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
        </a>
        <a href="#" class="sidebar-toggler flex-shrink-0">
          <i class="fa fa-bars"></i>
        </a>
        <form class="d-none d-md-flex ms-4">
          <input class="form-control border-0" type="search" placeholder="Tìm kiếm">
        </form>
        <div class="navbar-nav align-items-center ms-auto">
          <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
              <i class="fa fa-bell me-lg-2"></i>
              <span class="d-none d-lg-inline-flex">Thông báo</span>
            </a>
            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
              <a href="#" class="dropdown-item">
                <h6 class="fw-normal mb-0">Cập nhật hồ sơ</h6>
                <small>15 phút trước</small>
              </a>
              <hr class="dropdown-divider">
              <a href="#" class="dropdown-item">
                <h6 class="fw-normal mb-0">Thêm người dùng</h6>
                <small>15 phút trước</small>
              </a>
              <hr class="dropdown-divider">
              <a href="#" class="dropdown-item">
                <h6 class="fw-normal mb-0">Thay đổi mật khẩu</h6>
                <small>15 phút trước</small>
              </a>
              <hr class="dropdown-divider">
              <a href="#" class="dropdown-item text-center">Xem tất cả thông báo</a>
            </div>
          </div>
          <%if (user!=null){%>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/LogoutController">Đăng xuất</a></li>
          <% } %>
        </div>
      </nav>
      <!-- Navbar End -->