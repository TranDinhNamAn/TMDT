package Controller;

import DAO.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ProductDAO;

import java.io.IOException;

@WebServlet("/admin/DeleteEmployeeController")
public class DeleteEmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int empID = Integer.parseInt(idParam);

                UserDao user = new UserDao();
                user.deleteEmployee(empID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sau khi xóa xong, chuyển hướng về trang danh sách nv
        response.sendRedirect("GetAllEmployeeController");
    }
}
