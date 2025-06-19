package Controller;

import DAO.UserDao;
import Model.User;
import Model.Roles;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/EditEmployeeController")
public class EditEmployeeController extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("id"));

            User employee = userDao.findUserById(userId);
            List<Roles> allRoles = userDao.getAllRoles();
            List<Roles> userRoles = userDao.getUserRole(userId);

            request.setAttribute("employee", employee);
            request.setAttribute("allRoles", allRoles);
            request.setAttribute("userRoles", userRoles);

            request.getRequestDispatcher("/admin/editEmployee.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String username = request.getParameter("username");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("userEmail");
            String phone = request.getParameter("userPhone");

            // Cập nhật thông tin người dùng
            userDao.updateUser(userId, username, fullName, email, phone);

            // Cập nhật quyền
            String[] selectedRoles = request.getParameterValues("userRole");
            userDao.clearUserRoles(userDao.getEmployeeIdByUserId(userId)); // Xoá hết quyền cũ

            if (selectedRoles != null) {
                for (String roleName : selectedRoles) {
                    int roleId = userDao.getRoleIdByName(roleName);
                    if (roleId != -1) {
                        userDao.addRoles(userDao.getEmployeeIdByUserId(userId), roleId);
                    }
                }
            }

            response.sendRedirect("GetAllEmployeeController"); // hoặc load lại list nhân viên
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
