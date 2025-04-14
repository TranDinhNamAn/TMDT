
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Lọc Xanh - Đăng Nhập</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <!-- link bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- font sowesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
</head>
<style>
    #spinner {
        opacity: 0;
        visibility: hidden;
        transition: opacity .5s ease-out, visibility 0s linear .5s;
        z-index: 99999;
    }

    #spinner.show {
        transition: opacity .5s ease-out, visibility 0s linear 0s;
        visibility: visible;
        opacity: 1;
    }
    a{
        text-decoration: none;
        color: red;
    }
    .btn {
        transition: .5s;

    }
    .btn.btn-primary {
        color: #FFFFFF;
        background-color: red;
    }

    .error-message{
        padding-bottom: 10px;
        color: red;
    }

</style>
<body>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->

    <!-- Sign In Start -->
    <div class="container-fluid">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3">
                    <div class="text-center">
                        <h3>Đăng Nhập</h3>
                    </div>
                    <form action="signin" method="post">
                      <h6 style="color: red"><%=request.getAttribute("notification")!=null?request.getAttribute("notification"):""%></h6>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInput" name="username" placeholder="Tên tài khoản" required>
                            <label for="floatingInput">Tên tài khoản</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" required>
                            <label for="floatingPassword">Mật Khẩu</label>
                        </div>
                        <div class="d-flex align-items-center justify-content-between mb-4">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe" required>
                                <label class="form-check-label" for="exampleCheck1">Lưu đăng nhập</label>
                            </div>
                            <a href="<%=request.getContextPath()%>/forgotPassword">Quên mật khẩu</a>
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4">Đăng Nhập</button>
                    </form>
                    <p class="text-center mb-0">Bạn chưa có tài khoản? <a href="<%= request.getContextPath() %>/signup"> Đăng Ký</a></p>
                </div>
            </div>
        </div>
    </div>
    <!-- Sign In End -->
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- Template Javascript -->
<script>
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
</script>
</body>
</html>
