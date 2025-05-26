<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Thanh toán thành công</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
  <div class="container mt-5">
    <div class="card shadow p-4 text-center">
      <h1 class="text-success mb-4">🎉 Thanh toán thành công!</h1>
      <p>Cảm ơn bạn đã đặt hàng.</p>
      <a href="${pageContext.request.contextPath}/shopping-cart" class="btn btn-primary mt-3">Quay lại trang chủ</a>
    </div>
  </div>
</body>
</html>
