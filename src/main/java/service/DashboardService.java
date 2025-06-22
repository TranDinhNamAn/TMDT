package service;

import DAO.DashboardDAO;

import java.util.Map;

public class DashboardService {
    private final DashboardDAO dashboardDAO;

    public DashboardService() {
        this.dashboardDAO = new DashboardDAO();
    }

    // Tổng doanh thu hôm nay
    public int getTodayRevenue() {
        return dashboardDAO.getTodayRevenue();
    }

    // Số đơn hàng hôm nay
    public int getTodayOrderCount() {
        return dashboardDAO.getTodayOrderCount();
    }

    // Tổng sản phẩm bán hôm nay
    public int getTodaySoldProductCount() {
        return dashboardDAO.getTodaySoldProductCount();
    }

    // Tổng doanh thu tháng hiện tại
    public int getMonthlyRevenue() {
        return dashboardDAO.getMonthlyRevenue();
    }

    // Doanh thu theo từng ngày trong tuần (biểu đồ cột)
    public Map<String, Integer> getWeeklyRevenue() {
        return dashboardDAO.getWeeklyRevenue();
    }

    // Top 10 sản phẩm bán chạy theo phần trăm (biểu đồ tròn)
    public Map<String, Double> getTopSellingProductsPercentage() {
        return dashboardDAO.getTopSellingProductsPercentage();
    }
}
