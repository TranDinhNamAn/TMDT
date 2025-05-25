<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Product" %>
<%@ page import="Model.Categories" %>
<%@ page import="java.util.List" %>
<jsp:include page="header.jsp" />

<%
  Product product = (Product) request.getAttribute("product");
  if (product == null) {
%>
<div class="alert alert-danger">Không tìm thấy sản phẩm!</div>
<%
    return;
  }
%>

<div class="container-fluid pt-4 px-4">
  <div class="bg-light rounded h-100 p-4">
    <h6 class="mb-4">Chỉnh sửa Sản Phẩm</h6>
    <form action="<%=request.getContextPath()%>/admin/EditProductController" method="POST" enctype="multipart/form-data">
      <!-- Hidden product ID -->
      <input type="hidden" name="productId" value="<%= product.getProductID() %>">

      <!-- Product Name -->
      <div class="mb-3">
        <label for="productName" class="form-label">Tên Sản Phẩm</label>
        <input type="text" class="form-control" id="productName" name="productName"
               value="<%= product.getNameProduct() %>" required>
      </div>

      <!-- Price, Category, Stock -->
      <div class="mb-3 row">
        <div class="col-md-4">
          <label for="productPrice" class="form-label">Giá</label>
          <input type="number" class="form-control" id="productPrice" name="productPrice"
                 value="<%= product.getPrice() %>" required>
        </div>
        <div class="col-md-4">
          <label for="productCategoryType" class="form-label">Loại Sản Phẩm</label>
          <select class="form-control" id="productCategoryType" name="productCategoryType" required>
            <%
              List<Categories> categoryList = (List<Categories>) request.getAttribute("categories");
              if (categoryList != null) {
                for (Categories c : categoryList) {
            %>
            <option value="<%= c.getCategoriesID() %>"><%= c.getName() %></option>
            <%
                }
              }
            %>
          </select>
        </div>
        <div class="col-md-4">
          <label for="productStock" class="form-label">Số lượng</label>
          <input type="number" class="form-control" id="productStock" name="productStock"
                 value="<%= product.getStock() %>" required>
        </div>
      </div>

      <!-- Description -->
      <div class="mb-3">
        <label for="productDescription" class="form-label">Mô Tả</label>
        <textarea class="form-control" id="productDescription" name="productDescription"
                  rows="4" required><%= product.getDescription() %></textarea>
      </div>

      <!-- Image Upload -->
      <div class="mb-3">
        <label for="productImages" class="form-label">Cập nhật hình ảnh mới (nếu muốn)</label>
        <input type="file" class="form-control" id="productImages" name="productImages" accept="image/*">
      </div>

      <!-- Existing Image -->
      <div class="mb-3">
        <label class="form-label">Hình ảnh hiện tại:</label><br>
        <%
          String imageUrl = product.getImage();
          if (imageUrl != null && !imageUrl.isEmpty()) {
        %>
        <img src="<%= request.getContextPath() + "/Image/" + imageUrl %>"
             class="img-thumbnail" style="width: 120px; height: 120px;" />
        <%
        } else {
        %>
        <p>Chưa có hình ảnh.</p>
        <%
          }
        %>
      </div>

      <!-- Submit Button -->
      <div class="text-center">
        <button type="submit" class="btn btn-primary">Lưu lại</button>
      </div>
    </form>
  </div>
</div>

<jsp:include page="../admin/footer.jsp" />