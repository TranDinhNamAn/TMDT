package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.DashboardService;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/admin/dashboard")
public class DashboardController extends HttpServlet {
    private final DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Gọi service để lấy dữ liệu box tổng quan
        int todayRevenue = dashboardService.getTodayRevenue();
        int todayOrderCount = dashboardService.getTodayOrderCount();
        int todaySoldProducts = dashboardService.getTodaySoldProductCount();
        int monthRevenue = dashboardService.getMonthlyRevenue();

        // Gọi dữ liệu biểu đồ
        Map<String, Integer> weeklyRevenue = dashboardService.getWeeklyRevenue();
        Map<String, Double> topProducts = dashboardService.getTopSellingProductsPercentage();

        // Truyền sang JSP
        req.setAttribute("todayRevenue", todayRevenue);
        req.setAttribute("todayOrderCount", todayOrderCount);
        req.setAttribute("todaySoldProducts", todaySoldProducts);
        req.setAttribute("monthRevenue", monthRevenue);

        req.setAttribute("weeklyRevenue", weeklyRevenue);
        req.setAttribute("topProducts", topProducts);

        req.getRequestDispatcher("/admin/indexAdmin.jsp").forward(req, resp);
    }
}
