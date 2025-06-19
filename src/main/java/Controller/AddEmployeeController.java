package Controller;

import DAO.UserDao;
import Model.Roles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AddEmployeeController")
public class AddEmployeeController extends HttpServlet {
    private final UserDao userDao = new UserDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<Roles> allRoles = userDao.getAllRoles();
        request.setAttribute("allRoles", allRoles);
        System.out.println(allRoles.size());
        request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullName");
        String phone = request.getParameter("userPhone");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String[] selectedRoles = request.getParameterValues("userRole");
        System.out.println("username = '" + username + "'");
        System.out.println("fullname = '" + fullname + "'");
        System.out.println("phone = '" + phone + "'");
        System.out.println("email = '" + email + "'");
        System.out.println("password = '" + password + "'");


        // Kiểm tra rỗng
        if (username == null || username.trim().isEmpty() ||
                fullname == null || fullname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                phone == null || phone.trim().isEmpty() ||
                password == null || password.trim().isEmpty()
                ) {
            System.out.println("Thông tin đăng ký không hợp lệ!");
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
            return;
        }

        // Kiểm tra username/email trùng lặp
        if (userDao.findUserByUsernameOrEmail(username) != null) {
            System.out.println("Username đã tồn tại.");
            request.setAttribute("error", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
            return;
        }

        if (userDao.findUserByUsernameOrEmail(email) != null) {
            System.out.println("Email đã tồn tại.");
            request.setAttribute("error", "Email đã tồn tại!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email không được để trống!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
            return;
        }

        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println("Password (hashed): " + hashedPassword);

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("error", "Tên đăng nhập không được để trống!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
            return;
        }

        // Đảm bảo name không bị null
        String name = (username != null && !username.trim().isEmpty()) ? username : "Người dùng chưa đặt tên";

        User newUser = new User(
                email,
                phone,
                username,
                hashedPassword,
                false, // Giá trị mặc định cho isBlocked
                User.Provider.LOCAL, // Giá trị cho provider
                "https://th.bing.com/th/id/OIP.bvgvfalKJGM4rU-nDAJ3aQHaHa?rs=1&pid=ImgDetMain" // ProfilePicture
                ,fullname
        );

        // Gọi registerUser
        boolean isRegistered = userDao.registerUser(newUser);
        if (isRegistered) {
            User registeredUser = userDao.findUserByUsernameOrEmail(username);
            int employeeId = userDao.addEmployee(registeredUser.getUserID());
            for (String roleName : selectedRoles) {
                int roleId = userDao.getRoleIdByName(roleName);
                if (roleId != -1) {
                    userDao.addRoles(employeeId, roleId);
                }
            }

            System.out.println("Thêm nv thành công, chuyển hướng sang ds nv.");
            response.sendRedirect(request.getContextPath() + "/admin/GetAllEmployeeController");
        } else {
            System.out.println("Đăng ký thất bại!");
            request.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại!");
            request.getRequestDispatcher("/admin/addUsers.jsp").forward(request, response);
        }
    }
}