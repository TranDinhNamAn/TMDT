package Controller;

import DAO.CartDB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/update-order")
public class UpdateOrderStatusController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int orderID = Integer.parseInt(request.getParameter("orderId"));
            CartDB orderDAO = new CartDB();
            String status = request.getParameter("status");
            System.out.println(orderID);
            System.out.println(status);
            boolean success = orderDAO.updateOrder(orderID, status);

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.sendRedirect("GetAllOrdersController");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.sendRedirect("GetAllOrdersController");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("GetAllOrdersController");
        }
    }
}

