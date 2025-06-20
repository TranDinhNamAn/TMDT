package Controller;

import DAO.CartDB;
import Model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/GetAllOrdersController")
public class GetAllOrdersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {


            CartDB orderDB = new CartDB();
            List<Order> orderList = orderDB.getAllOrders();
            System.out.println("order: " + orderList);

            request.setAttribute("orders", orderList);
            request.getRequestDispatcher("listOrders.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải đơn hàng");
        }
    }
}

