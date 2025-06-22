package DAO;

import Database.DatabaseConnection;
import org.jdbi.v3.core.Jdbi;

import java.util.LinkedHashMap;
import java.util.Map;

public class DashboardDAO {

    private final Jdbi jdbi;

    public DashboardDAO() {
        this.jdbi = DatabaseConnection.getJdbi();
    }

    // 1. Doanh thu hôm nay
    public int getTodayRevenue() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT COALESCE(SUM(TotalAmount), 0)
                FROM orders
                WHERE DATE(CreateDate) = CURRENT_DATE
            """).mapTo(Integer.class).one()
        );
    }

    // 2. Số đơn hàng hôm nay
    public int getTodayOrderCount() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT COUNT(*)
                FROM orders
                WHERE DATE(CreateDate) = CURRENT_DATE
            """).mapTo(Integer.class).one()
        );
    }

    // 3. Số sản phẩm bán ra hôm nay
    public int getTodaySoldProductCount() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT COALESCE(SUM(Quantity), 0)
                FROM orderdetail od
                JOIN orders o ON od.OrderID = o.OrderID
                WHERE DATE(o.CreateDate) = CURRENT_DATE
            """).mapTo(Integer.class).one()
        );
    }

    // 4. Tổng doanh thu trong tháng hiện tại
    public int getMonthlyRevenue() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
                SELECT COALESCE(SUM(TotalAmount), 0)
                FROM orders
                WHERE MONTH(CreateDate) = MONTH(CURDATE()) AND YEAR(CreateDate) = YEAR(CURDATE())
            """).mapTo(Integer.class).one()
        );
    }

    // 5. Doanh thu theo từng ngày trong tuần hiện tại (bar chart)
    public Map<String, Integer> getWeeklyRevenue() {
        return jdbi.withHandle(handle ->
                handle.createQuery("""
            SELECT 
                DATE_FORMAT(CreateDate, '%a') AS short_day,
                COALESCE(SUM(TotalAmount), 0) AS revenue
            FROM orders
            WHERE YEARWEEK(CreateDate, 1) = YEARWEEK(CURDATE(), 1)
            GROUP BY short_day
            ORDER BY FIELD(short_day, 'Mon','Tue','Wed','Thu','Fri','Sat','Sun')
        """).reduceRows(new LinkedHashMap<String, Integer>(), (map, rowView) -> {
                    String day = rowView.getColumn("short_day", String.class);
                    int revenue = rowView.getColumn("revenue", Integer.class);
                    map.put(day, revenue);
                    return map; // quan trọng!
                })
        );
    }
    // 6. Top 10 sản phẩm bán chạy theo % số lượng (pie chart)
    public Map<String, Double> getTopSellingProductsPercentage() {
        return jdbi.withHandle(handle -> {
            int totalSold = handle.createQuery("""
            SELECT COALESCE(SUM(Quantity), 0)
            FROM orderdetail
        """).mapTo(Integer.class).one();

            if (totalSold == 0) return new LinkedHashMap<>();

            return handle.createQuery("""
            SELECT p.NameProduct, SUM(od.Quantity) AS total
            FROM orderdetail od
            JOIN product p ON od.ProductID = p.ProductID
            GROUP BY p.NameProduct
            ORDER BY total DESC
            LIMIT 10
        """).reduceRows(new LinkedHashMap<String, Double>(), (map, rowView) -> {
                String name = rowView.getColumn("NameProduct", String.class);
                int quantity = rowView.getColumn("total", Integer.class);
                double percent = quantity * 100.0 / totalSold;
                map.put(name, Math.round(percent * 10.0) / 10.0); // round 1 digit
                return map; // quan trọng
            });
        });
    }
}
