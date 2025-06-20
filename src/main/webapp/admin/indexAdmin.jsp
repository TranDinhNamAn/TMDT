<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />

<!-- Box Tổng Quan Start -->
<div class="container-fluid pt-4 px-4">
  <div class="row g-4">
    <div class="col-sm-6 col-xl-3">
      <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
        <i class="fa fa-chart-line fa-3x text-primary"></i>
        <div class="ms-3">
          <p class="mb-2">Doanh thu hôm nay</p>
          <h6 class="mb-0"><%= request.getAttribute("todayRevenue") %>đ</h6>
        </div>
      </div>
    </div>
    <div class="col-sm-6 col-xl-3">
      <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
        <i class="fa fa-chart-bar fa-3x text-primary"></i>
        <div class="ms-3">
          <p class="mb-2">Đơn hàng hôm nay</p>
          <h6 class="mb-0"><%= request.getAttribute("todayOrderCount") %></h6>
        </div>
      </div>
    </div>
    <div class="col-sm-6 col-xl-3">
      <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
        <i class="fa fa-chart-area fa-3x text-primary"></i>
        <div class="ms-3">
          <p class="mb-2">Sản phẩm bán được</p>
          <h6 class="mb-0"><%= request.getAttribute("todaySoldProducts") %></h6>
        </div>
      </div>
    </div>
    <div class="col-sm-6 col-xl-3">
      <div class="bg-light rounded d-flex align-items-center justify-content-between p-4">
        <i class="fa fa-chart-pie fa-3x text-primary"></i>
        <div class="ms-3">
          <p class="mb-2">Tổng doanh thu trong tháng</p>
          <h6 class="mb-0"><%= request.getAttribute("monthRevenue") %>đ</h6>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Box Tổng Quan End -->


<!-- Biểu đồ Start -->
<div class="container-fluid pt-4 px-4">
  <div class="row g-4">

    <!-- Biểu đồ cột: Doanh thu theo tuần -->
    <div class="col-sm-12 col-xl-6">
      <div class="bg-light text-center rounded p-4">
        <h6 class="mb-4">Doanh thu theo tuần</h6>
        <div style="height: 220px; display: flex; align-items: flex-end; justify-content: space-between;">
          <%
            Map<String, Integer> weeklyRevenue = (Map<String, Integer>) request.getAttribute("weeklyRevenue");
            if (weeklyRevenue != null) {
              String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
              String[] labels = {"T2", "T3", "T4", "T5", "T6", "T7", "CN"};
              int i = 0;
              for (String day : days) {
                Integer revenue = weeklyRevenue.getOrDefault(day, 0);
                int height = Math.min(revenue / 10000, 200); // giới hạn chiều cao để không quá dài
          %>
          <div style="text-align: center;">
            <div style="background: #0d6efd; width: 30px; height: <%= height %>px; margin: auto;"
                 title="<%= labels[i] %> - <%= revenue %>đ"></div>
            <small><%= labels[i] %></small>
          </div>
          <%
              i++;
            }
          } else {
          %>
          <p style="color: red;">Không có dữ liệu doanh thu tuần để hiển thị.</p>
          <%
            }
          %>
        </div>
      </div>
    </div>


    <!-- Biểu đồ tròn: Top 10 sản phẩm -->
    <div class="col-sm-12 col-xl-6">
      <div class="bg-light text-center rounded p-4">
        <h6 class="mb-4">Top 10 sản phẩm bán chạy</h6>
        <div style="display: flex; justify-content: center; align-items: center; gap: 20px; flex-wrap: wrap;">
          <%
            Object topAttr = request.getAttribute("topProducts");
            if (topAttr != null && topAttr instanceof Map) {
              Map<String, Double> topProducts = (Map<String, Double>) topAttr;
              if (!topProducts.isEmpty()) {
                String[] colors = {"#0d6efd", "#198754", "#ffc107", "#dc3545", "#6f42c1", "#20c997",
                        "#fd7e14", "#0dcaf0", "#adb5bd", "#212529"};
                int colorIndex = 0;
                double start = 0;
                StringBuilder gradient = new StringBuilder("conic-gradient(");

                for (Map.Entry<String, Double> entry : topProducts.entrySet()) {
                  double percent = entry.getValue();
                  double end = start + percent;
                  String color = colors[colorIndex % colors.length];
                  gradient.append(color)
                          .append(" ").append(start).append("% ").append(end).append("%,");
                  start = end;
                  colorIndex++;
                }

                if (gradient.charAt(gradient.length() - 1) == ',') gradient.setLength(gradient.length() - 1);
                gradient.append(")");
          %>

          <!-- Vẽ biểu đồ tròn -->
          <div style="width: 200px; height: 200px; border-radius: 50%;
                  background: <%= gradient.toString() %>;
                  border: 3px solid white;">
          </div>

          <!-- Chú thích -->
          <ul style="list-style: none; padding-left: 0; margin: 0; font-size: 14px; text-align: left;">
            <%
              colorIndex = 0;
              for (Map.Entry<String, Double> entry : topProducts.entrySet()) {
                String color = colors[colorIndex % colors.length];
            %>
            <li>
              <span style="color:<%= color %>">●</span>
              <%= entry.getKey() %> - <%= String.format("%.2f", entry.getValue()) %>%
            </li>
            <%
                colorIndex++;
              }
            %>
          </ul>

          <%
          } else {
          %>
          <p style="color: red;">Không có dữ liệu sản phẩm để hiển thị.</p>
          <%
            }
          } else {
          %>
          <p style="color: red;">Dữ liệu topProducts không tồn tại hoặc không đúng định dạng.</p>
          <%
            }
          %>
        </div>
      </div>
    </div>


  </div>
</div>
<!-- Biểu đồ End -->

<jsp:include page="footer.jsp" />

