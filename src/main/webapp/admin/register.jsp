<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>Lọc Xanh - Đăng ký</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Trang đăng ký Lọc Xanh" name="keywords">
    <meta content="Đăng ký hệ thống Lọc Xanh" name="description">

    <!-- Favicon -->
    <link href="${pageContext.request.contextPath}/assets/admin/img/favicon.icon" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/admin/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .btn-primary {
            color: #fff !important;
        }

        .register-title {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Đăng ký Start -->
    <div class="container-fluid">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                    <div class="register-title">
                        <h3>Đăng Ký Tài Khoản</h3>
                    </div>

                    <form action="<%= request.getContextPath() %>/RegisterController" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingUsername" name="username" placeholder="Tên đăng nhập" required>
                            <label for="floatingUsername">Tên đăng nhập</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingEmail" name="email" placeholder="Email" required>
                            <label for="floatingEmail">Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Mật khẩu" required>
                            <label for="floatingPassword">Mật khẩu</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingConfirmPassword" name="confirmPassword" placeholder="Xác nhận mật khẩu" required>
                            <label for="floatingConfirmPassword">Xác nhận mật khẩu</label>
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100">Đăng Ký</button>
                    </form>

                    <%-- Hiển thị thông báo lỗi nếu có --%>
                    <% if (request.getAttribute("error") != null) { %>
                    <p class="text-danger text-center"><%= request.getAttribute("error") %></p>
                    <% } %>

                    <p class="text-center mt-4">Đã có tài khoản? <a href="login.jsp">Đăng Nhập</a></p>
                </div>
            </div>
        </div>
    </div>
    <!-- Đăng ký End -->
</div>

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>