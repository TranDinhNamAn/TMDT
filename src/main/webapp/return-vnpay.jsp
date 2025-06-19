<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Kết quả thanh toán</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container mt-5">
  <div class="card shadow p-4">
    <h3 class="text-center">
      <c:choose>
        <c:when test="${vnp_ResponseCode == '00' && vnp_TransactionStatus == '00'}">
          <span class="text-success">🎉 Thanh toán thành công!</span>
        </c:when>
        <c:otherwise>
          <span class="text-danger">❌ Thanh toán thất bại!</span>
        </c:otherwise>
      </c:choose>
    </h3>

    <ul class="list-group mt-4">
      <li class="list-group-item"><strong>Mã giao dịch:</strong> ${vnp_TransactionNo}</li>
      <li class="list-group-item "><strong>Số tiền:</strong> <span class="format-money-vnd">${vnp_Amount}</span> VNĐ</li>
      <li class="list-group-item"><strong>Ngân hàng:</strong> ${vnp_BankCode}</li>
      <li class="list-group-item"><strong>Thời gian thanh toán:</strong> ${vnp_PayDate}</li>
      <li class="list-group-item"><strong>Thông tin đơn hàng:</strong> ${vnp_OrderInfo}</li>
    </ul>

    <div class="mt-3 text-center">
      <a href="home" class="btn btn-primary">Quay lại</a>
    </div>
  </div>
</div>
<script src="js/format-string.js"></script>
<script >
  console.log(${vnp_ResponseCode == '00' && vnp_TransactionStatus == '00'});
  if(${vnp_ResponseCode == '00' && vnp_TransactionStatus == '00'}){
    $.ajax({
      url: "pay-vnpay",
      type: "POST",
      success: function (response) {
        console.log("Success", response);
      },
      error: function (error) {
        console.error("Error , error");
      }
  })}
  else{
    document.querySelector('.mt-3>a').href = "/order-page-load";
  }
</script>
</body>
</html>