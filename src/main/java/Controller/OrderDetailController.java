package Controller;

import DAO.CartDB;
import Model.OrderDetail;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/order-detail")
public class OrderDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            CartDB dao = new CartDB();
            List<OrderDetail> details = dao.getOrderDetailsByOrderID(orderID);
            System.out.println("orderID: " + orderID + "list: " + details.size());

            request.setAttribute("orderID", orderID);
            request.setAttribute("details", details);
            request.getRequestDispatcher("order-detail.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

