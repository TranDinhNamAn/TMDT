<%@ page import="Model.User" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
  <meta charset="UTF-8">
  <meta name="description" content="Ashion Template">
  <meta name="keywords" content="Ashion, unica, creative, html">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Lọc Xanh | Trang chủ</title>

  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css2?family=Cookie&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700;800;900&display=swap" rel="stylesheet">

  <!-- Css Styles -->
  <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
  <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
  <link rel="stylesheet" href="assets/css/jquery-ui.min.css" type="text/css">
  <link rel="stylesheet" href="assets/css/magnific-popup.css" type="text/css">
  <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
  <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
  <link rel="stylesheet" href="assets/css/style.css" type="text/css">
  <link rel="stylesheet" href="assets/fontawesome/css/font-awesome.css">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

  <style>
    .header__right__widget a,
    .header__menu a {
      text-decoration:none !important;
    }

    .header__right {
      display: flex;
      align-items: center;
      justify-content: flex-end;
    }

    .dropdown {
      margin-left: 20px;
    }

    .user-menu {
      display: flex;
      align-items: center;
      cursor: pointer;
    }

    .user-menu img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }

    .dropdown-menu {
      text-align: left;
      min-width: 160px;
    }
  </style>
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
  <div class="loader"></div>
</div>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
  <div class="offcanvas__close">+</div>
  <ul class="offcanvas__widget">
    <li><span class="icon_search search-switch"></span></li>
    <li><a href="<%= request.getContextPath() %>/wish_lists.jsp"><span class="icon_heart_alt"></span>
      <div class="tip">2</div>
    </a></li>
    <li><a href="<%= request.getContextPath() %>/shop-cart.jsp"><span class="icon_bag_alt"></span>
      <div class="tip">0</div>
    </a></li>
  </ul>
  <div class="offcanvas__logo">
    <a href="./index.jsp"><img src="assets/img/logo.png" alt=""></a>
  </div>
  <div id="mobile-menu-wrap"></div>
  <div class="offcanvas__auth">
    <a href="#">Login</a>
    <a href="#">Register</a>
  </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
  <div class="container-fluid">
    <div class="row">
      <div class="col-xl-3 col-lg-2">
        <div class="header__logo">
          <a href="./index.jsp"><img src="assets/img/logo.png" alt=""></a>
        </div>
      </div>
      <div class="col-xl-6 col-lg-7">
        <nav class="header__menu">
          <ul>
            <li class="<%= request.getRequestURI().contains("index.jsp") ? "active" : "" %>"><a href="index.jsp">TRANG CHỦ</a></li>
            <li class="<%= request.getRequestURI().contains("introduction.jsp") ? "active" : "" %>"><a href="introduction.jsp">GIỚI THIỆU</a></li>
            <li class="<%= request.getRequestURI().contains("shop.jsp") ? "active" : "" %>"><a href="shop.jsp">SẢN PHẨM</a></li>
            <li class="<%= request.getRequestURI().contains("service.jsp") ? "active" : "" %>"><a href="service.jsp">DỊCH VỤ</a></li>
            <li class="<%= request.getRequestURI().contains("blog.jsp") ? "active" : "" %>"><a href="blog.jsp">TIN TỨC</a></li>
            <li class="<%= request.getRequestURI().contains("contact.jsp") ? "active" : "" %>"><a href="contact.jsp">LIÊN HỆ</a></li>
          </ul>
        </nav>

      </div>
      <div class="col-lg-3">

        <div class="header__right">
          <ul class="header__right__widget" style="margin-right: 20px; margin-bottom: 0px;">
            <!-- Wishlist -->
            <li><a href="wish_lists.jsp"><span class="icon_heart_alt"></span>
              <div class="tip">2</div>
            </a></li>
            <!-- Giỏ hàng -->
            <li><a href="shop-cart.jsp"><span class="icon_bag_alt"></span>
              <div class="tip">2</div>
            </a></li>
          </ul>


          <%
            User user = (User) session.getAttribute("user");
            String avatar = user != null ? user.getProfilePicture() : "https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain";
            String username = user != null ? user.getUserName() : "Khách";
          %>

          <% if (user != null) { %>
          <div class="dropdown user-menu">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
              <img class="rounded-circle me-lg-2" src="<%= avatar %>" alt="Avatar" style="width: 40px; height: 40px;">
              <span class="d-none d-lg-inline-flex">
              <%= username.length() > 8 ? username.substring(0, 8) + "..." : username %>
              </span>
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
              <li><a class="dropdown-item" href="<%=request.getContextPath()%>/profile.jsp">Thông tin cá nhân</a></li>
              <li><a class="dropdown-item" href="<%=request.getContextPath()%>/LogoutController">Đăng xuất</a></li>
            </ul>
          </div>
          <% } else { %>
          <!-- Khi chưa đăng nhập, hiển thị nút Đăng nhập và Đăng ký -->
          <div class="header__right__auth">
            <a href="<%=request.getContextPath()%>/admin/login.jsp">Đăng nhập</a>
            <a href="<%=request.getContextPath()%>/admin/register.jsp">Đăng ký</a>
          </div>
          <% } %>
        </div>


      </div>
    </div>
    <div class="canvas__open">
      <i class="fa fa-bars"></i>
    </div>

    <!--          tìm kiếm-->
    <div class="row justify-content-center">
      <div class="col-md-7" style="margin-bottom: 20px;">
        <form action="#" class="d-flex">
          <input type="text" class="form-control search-input me-2" placeholder="Nhập tên sản phẩm bạn muốn tìm kiếm?">
          <button type="submit" class="btn btn-danger search-btn">
                  <span class="icon_search search-switch">
                  </span>
          </button>
        </form>
      </div>
    </div>
  </div>

</header>
<!-- Header Section End -->
