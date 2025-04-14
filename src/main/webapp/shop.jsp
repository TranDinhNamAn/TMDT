<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="/header.jsp" />

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="breadcrumb__links">
          <a href="index.jsp"><i class="fa fa-home"></i>TRANG CHỦ</a>
          <span>SẢN PHẨM</span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Breadcrumb End -->

<div class="filter__item filter__item__page__detail">
  <div class="row">
    <div class="col-lg-4 col-md-5">
      <div class="filter__sort ">
        <span>Sắp Xếp</span>
        <select>
          <option value="0">Mặc định</option>
          <option value="0">Giá từ cao tới thấp</option>
          <option value="0">Giá từ thấp đến cao</option>
        </select>
      </div>
    </div>
  </div>
</div>

<!-- Shop Section Begin -->
<section class="shop spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-3">
        <div class="shop__sidebar">
          <div class="sidebar__categories">
            <div class="section-title">
              <h4>Phân Loại</h4>
            </div>
            <div class="categories__accordion">
              <div class="accordion" id="accordionExample">
                <div class="card">
                  <div class="card-heading active">
                    <a data-toggle="collapse" data-target="#collapseOne">Lọc nước RO</a>
                  </div>
                  <div id="collapseOne" class="collapse show" data-parent="#accordionExample">
                    <div class="card-body">
                      <ul>
                        <li><a href="#" onclick="filterProducts('all', 'ro')">Tất cả</a></li>
                        <li><a href="#" onclick="filterProducts('Sunhouse','ro')">Sunhouse</a></li>
                        <li><a href="#" onclick="filterProducts('Toshiba','ro')">Toshiba</a></li>
                        <li><a href="#" onclick="filterProducts('Kangaroo','ro')">Kangaroo</a></li>
                        <li><a href="#" onclick="filterProducts('Karofi','ro')">Karofi</a></li>
                      </ul>

                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-heading">
                    <a data-toggle="collapse" data-target="#collapseTwo">Nóng - Nguội - Lạnh</a>
                  </div>
                  <div id="collapseTwo" class="collapse" data-parent="#accordionExample">
                    <div class="card-body">
                      <ul>
                        <li><a href="#" onclick="filterProducts('all', 'nongNguoiLanh')">Tất cả</a></li>
                        <li><a href="#" onclick="filterProducts('Sunhouse', 'nongNguoiLanh')">Sunhouse</a></li>
                        <li><a href="#" onclick="filterProducts('Toshiba', 'nongNguoiLanh')">Toshiba</a></li>
                        <li><a href="#" onclick="filterProducts('Kangaroo', 'nongNguoiLanh')">Kangaroo</a></li>
                        <li><a href="#" onclick="filterProducts('Karofi', 'nongNguoiLanh')">Karofi</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-heading">
                    <a data-toggle="collapse" data-target="#collapseThree">Âm tủ - Để bàn</a>
                  </div>
                  <div id="collapseThree" class="collapse" data-parent="#accordionExample">
                    <div class="card-body">
                      <ul>
                        <li><a href="#" onclick="filterProducts('all', 'amBan')">Tất cả</a></li>
                        <li><a href="#" onclick="filterProducts('Sunhouse', 'amBan')">Sunhouse</a></li>
                        <li><a href="#" onclick="filterProducts('ROBOT', 'amBan')">AOSmith</a></li>
                        <li><a href="#" onclick="filterProducts('Kangaroo', 'amBan')">Kangaroo</a></li>
                        <li><a href="#" onclick="filterProducts('Karofi', 'amBan')">Karofi</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="card">
                  <div class="card-heading">
                    <a data-toggle="collapse" data-target="#collapseFour">Lọc điện giải ion kiềm</a>
                  </div>
                  <div id="collapseFour" class="collapse" data-parent="#accordionExample">
                    <div class="card-body">
                      <ul>
                        <li><a href="#" onclick="filterProducts('all', 'ionKiem')">Tất cả</a></li>
                        <li><a href="#" onclick="filterProducts('ROBOT', 'ionKiem')">ROBOT</a></li>
                        <li><a href="#" onclick="filterProducts('Kangen LeveLuk', 'ionKiem')">Kangen LeveLuk</a></li>
                        <li><a href="#" onclick="filterProducts('Panasonic', 'ionKiem')">Panasonic</a></li>
                        <li><a href="#" onclick="filterProducts('IonFarms', 'ionKiem')">IonFarms</a></li>
                      </ul>
                    </div>
                  </div>
                </div>

              </div>
            </div>
          </div>
          <div class="sidebar__filter">
            <div class="section-title">
              <h4>Chọn mức giá phù hợp</h4>
            </div>
            <div class="filter-range-wrap">
              <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                   data-min="0" data-max="50000000"></div>
              <div class="range-slider">
                <div class="price-input">
                  <p>Giá:</p>
                  <input type="text" id="minamount">
                  <input type="text" id="maxamount" style="margin: -70px;">
                </div>
              </div>
            </div>
            <button id="filter-price-btn">Lọc</button>
          </div>
          <div class="sidebar__sizes">
            <div class="section-title">
              <h4>Chế Độ Nước</h4>
            </div>
            <div class="size__list">
              <label>
                <input type="checkbox" id="feature-hot" onchange="applyFilters()" />Nước nóng
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-cold" onchange="applyFilters()" />Nước lạnh
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-plain" onchange="applyFilters()" /> Nước thường
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-warm" onchange="applyFilters()" /> Nước ấm
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
          <div class="sidebar__color">
            <div class="section-title">
              <h4>Tiện Ích</h4>
            </div>
            <div class="size__list color__list">
              <label>
                <input type="checkbox" id="feature-energy" onchange="applyFilters()" />Tiết kiệm điện
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-lock" onchange="applyFilters()" /> Khóa an toàn nước nóng
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-low" onchange="applyFilters()" />Chỉ báo mực nước thấp
                <span class="checkmark"></span>
              </label>
              <label>
                <input type="checkbox" id="feature-temp"  onchange="applyFilters()" />Đèn báo nhiệt độ nước
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-9 col-md-9">
        <div class="row product-row">
        </div>
      </div>
    </div>
    <div class="product__pagination">
      <a class="product__pagination__page2" href="#">1</a>
      <a href="#">2</a>
      <a href="#">3</a>
      <a href="#"><i class="fa fa-long-arrow-right"></i></a>
    </div>
  </div>
</section>
<!-- Shop Section End -->
<jsp:include page="/footer.jsp" />
<script src="${pageContext.request.contextPath}/js/main.js"></script>
