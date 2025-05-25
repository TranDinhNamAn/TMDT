package Controller;

import Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import DAO.UserDao;

@WebServlet("/admin/GetAllUserController")
public class GetAllUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay danh sach user tu csdl
        UserDao userDao = new UserDao();
        List<User> userList = userDao.getAllUsersWithAddresses();
        System.out.println(userList.size());
        request.setAttribute("user", userList);
        request.getRequestDispatcher("/admin/listUsers.jsp").forward(request, response);
    }
}
