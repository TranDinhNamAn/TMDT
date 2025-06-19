<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Product" %>
<jsp:include page="header.jsp" />

<div class="container-fluid pt-4 px-4">
  <div class="col-12">
    <div class="bg-light rounded h-100 p-4">
      <h6 class="mb-4">Danh sách sản phẩm</h6>

      <!-- Ô tìm kiếm sản phẩm -->
      <form id="searchForm" class="mb-3">
        <div class="input-group">
          <input type="text" id="searchInput" name="keyword" class="form-control" placeholder="Tìm kiếm sản phẩm...">
        </div>
      </form>

      <div class="table-responsive">
        <table class="table">
          <thead>
          <tr>
            <th>Loại</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Hình ảnh</th>
            <th>Tùy chọn</th>
          </tr>
          </thead>
          <tbody id="productTableBody">
          <%
            List<Product> productList = (List<Product>) request.getAttribute("products");
            if (productList != null) {
              for (Product p : productList) {
          %>
          <tr>
            <td><%= p.getCategories() %></td>
            <td><%= p.getNameProduct() %></td>
            <td><%= String.format("%,d", (long)p.getPrice()) %> VNĐ</td>
            <td><%= p.getStock() %></td>
            <td><%= p.getCreateDate() %></td>
            <td><%= p.getLastUpdateDate() %></td>
            <td>
              <img src="${pageContext.request.contextPath}/Image/<%= p.getImage() %>" alt="" style="width: 60px; height: 60px; object-fit: cover;">
            </td>
            <td>
              <a href="EditProductController?id=<%= p.getProductID() %>" class="text-warning me-2"><i class="fa fa-edit"></i></a>
              <a href="DeleteProductController?id=<%= p.getProductID() %>"
                 class="text-danger"
                 onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">
                <i class="fa fa-trash"></i>
              </a>
            </td>
          </tr>
          <%
            }
          } else {
          %>
          <tr>
            <td colspan="8" class="text-center">Không có sản phẩm nào</td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Script AJAX tìm kiếm -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  $(document).ready(function () {
    $('#searchInput').on('input', function () {
      let keyword = $(this).val().trim();
      $.ajax({
        type: 'POST',
        url:'${pageContext.request.contextPath}/admin/SearchProductController',
        data: { keyword: keyword },
        success: function (response) {
          $('#productTableBody').html(response);
        },
        error: function () {
          $('#productTableBody').html('<tr><td colspan="8" class="text-center text-danger">Lỗi khi tìm kiếm</td></tr>');
        }
      });
    });
  });
</script>

<jsp:include page="/admin/footer.jsp" />
