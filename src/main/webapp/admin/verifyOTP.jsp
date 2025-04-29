<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="vi">

<head>
    <meta charset="utf-8">
    <title>Lọc Xanh - Quên mật khẩu</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Trang quên mật khẩu Lọc Xanh" name="keywords">
    <meta content="Quên mật khẩu hệ thống Lọc Xanh" name="description">

    <!-- Favicon -->
    <link href="${pageContext.request.contextPath}/assets/admin/img/favicon.icon" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="${pageContext.request.contextPath}/assets/admin/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .btn-primary {
            color: #fff !important;
        }
        .reset-title {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Quên mật khẩu Start -->
    <div class="container-fluid">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                    <div class="reset-title">
                        <h3>Nhập Mã Xác Nhận</h3>
                    </div>
                    <form action="<%= request.getContextPath() %>/VerifyOTPController" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingCode" placeholder="Mã Xác Nhận" name="otp" required>
                            <label for="floatingCode">Mã Xác Nhận</label>
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Xác Nhận</button>
                    </form>
                    <%-- Hiển thị thông báo lỗi nếu có --%>
                    <% if (request.getAttribute("error") != null) { %>
                    <p class="text-danger text-center"><%= request.getAttribute("error") %></p>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <!-- Quên mật khẩu End -->
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>