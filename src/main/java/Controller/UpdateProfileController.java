//package Controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import Model.User;
//import service.UserService;
//
//import java.io.IOException;
//
//@WebServlet("/UpdateProfileController")
//public class UpdateProfileController extends HttpServlet {
//    private UserService userService;
//
//    @Override
//    public void init() throws ServletException {
//        userService = new UserService();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Kiểm tra người dùng có đang đăng nhập không
//        HttpSession session = request.getSession();
//        Integer userId = (Integer) session.getAttribute("userId");
//
//        if (userId == null) {
//            // Nếu chưa đăng nhập, điều hướng về trang login
//            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
//            return;
//        }
//
//        // Lấy thông tin người dùng từ database
//        User user = userService.getUserById(userId);
//
//        if (user == null) {
//            response.getWriter().println("Không tìm thấy thông tin người dùng.");
//            return;
//        }
//
//        // Gửi thông tin người dùng đến JSP
//        request.setAttribute("user", user);
//        request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
//    }
//
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Xác thực người dùng đang đăng nhập
//        HttpSession session = request.getSession();
//        Integer userId = (Integer) session.getAttribute("userId");
//
//        if (userId == null) {
//            response.sendRedirect(request.getContextPath() + "/admin/login.jsp"); // Chuyển hướng nếu chưa đăng nhập
//            return;
//        }
//
//        // Lấy dữ liệu từ form
//        String name = request.getParameter("name");
//        String username = request.getParameter("username");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//        String profilePicture = request.getParameter("profile_picture");
//
//        // Tạo đối tượng User từ dữ liệu form
//        User user = new User();
//        user.setId(userId);
//        user.setName(name);
//        user.setUsername(username);
//        user.setPhone(phone);
//        user.setAddress(address);
//        user.setProfilePicture(profilePicture);
//
//        // Gửi yêu cầu cập nhật thông tin qua Service
//        boolean isUpdated = userService.updateUser(user);
//
//        // Xử lý kết quả
//        if (isUpdated) {
//            request.setAttribute("message", "Cập nhật thành công!");
//            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response); // Không hủy session
//        } else {
//            request.setAttribute("message", "Cập nhật thất bại!");
//            request.getRequestDispatcher("/admin/profile.jsp").forward(request, response);
//        }
//    }
//
//}
