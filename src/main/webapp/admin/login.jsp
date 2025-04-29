<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>Lọc Xanh - Đăng nhập</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Trang đăng nhập Lọc Xanh" name="keywords">
    <meta content="Đăng nhập hệ thống Lọc Xanh" name="description">

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

    <!-- Custom CSS for Modifications -->
    <style>
        .login-title {
            text-align: center;
            margin-bottom: 20px;
        }

        .social-buttons {
            display: flex;
            justify-content: space-between;
        }

        .btn-google {
            background-color: #ca1515;
            color: #fff;
        }

        .btn-facebook {
            background-color: #ca1515;
            color: #fff;
        }

        .btn-google:hover, .btn-facebook:hover {
            opacity: 0.9;
        }
    </style>
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Đang tải...</span>
        </div>
    </div>
    <!-- Spinner End -->

    <!-- Đăng nhập Start -->
    <div class="container-fluid">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                    <div class="login-title">
                        <h3>Đăng Nhập</h3>
                    </div>

                    <form action="${pageContext.request.contextPath}/LoginController" method="post">                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="usernameOrEmail" placeholder="Tên đăng nhập hoặc email" name="usernameOrEmail">
                            <label for="usernameOrEmail">Tên đăng nhập hoặc Email</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password">
                            <label for="password">Mật khẩu</label>
                        </div>
                        <% if (request.getAttribute("error") != null) { %>
                        <p class="text-danger text-center"><%= request.getAttribute("error") %></p>
                        <% } %>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Đăng Nhập</button>
                    </form>
                    <p class="text-center">
                        <a href="forgotPassword.jsp">Quên mật khẩu?</a>
                        <br>
                        Chưa có tài khoản? <a href="register.jsp">Đăng Ký</a>
                    </p>
                    <hr>
                    <p class="text-center">Hoặc đăng nhập bằng:</p>
                    <div class="social-buttons">
                        <!-- Google OAuth -->
                        <a href="https://accounts.google.com/o/oauth2/auth?client_id=707845209669-kvivpovf381f31e821p2i7i6sn9126or.apps.googleusercontent.com&redirect_uri=http://localhost:8081/Gradle___com_example___TTWeb_1_0_SNAPSHOT_war/GoogleLoginController&response_type=code&scope=email profile"
                           class="btn btn-google py-2 w-50 me-2">
                            <i class="fab fa-google me-2"></i>Google
                        </a>

                        <a href="https://www.facebook.com/v10.0/dialog/oauth?client_id=1369237884391739&redirect_uri=http://localhost:8081/Gradle___com_example___TTWeb_1_0_SNAPSHOT_war/FacebookLoginController&response_type=code&scope=email,public_profile"
                           class="btn btn-facebook py-2 w-50">
                            <i class="fab fa-facebook-f me-2"></i>Facebook
                        </a>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Đăng nhập End -->
</div>

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/chart/chart.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/easing/easing.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/waypoints/waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/owlcarousel/owl.carousel.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/tempusdominus/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="${pageContext.request.contextPath}/assets/admin/js/main.js"></script>
</body>
</html>