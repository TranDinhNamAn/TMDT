package Controller;

import Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import DAO.UserDao;

@WebServlet("/admin/GetAllEmployeeController")
public class GetAllEmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay danh sach nv tu csdl
        UserDao userDao = new UserDao();
        List<User> employeeList = userDao.getEmployee();
        System.out.println(employeeList.size());
        request.setAttribute("employee", employeeList);
        request.getRequestDispatcher("/admin/listEmployees.jsp").forward(request, response);
    }
}
