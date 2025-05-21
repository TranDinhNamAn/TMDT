package Filter;

import DAO.UserDao;
import Model.Roles;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebFilter("/admin/*")
public class EmployeePermission implements Filter {

    private static final Map<String, String> protectedPaths = new HashMap<>();

    static {
        protectedPaths.put("/admin/AddProductController", "Employee_ADDPRODUCT");
        protectedPaths.put("/admin/EditProductController", "Employee_EDITPRODUCT");
        protectedPaths.put("/admin/DeleteProductController", "Employee_DELETEPRODUCT");
        protectedPaths.put("/admin/GetAllUserController", "Employee");
        // Thêm các nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI().replace(req.getContextPath(), "");

        String requiredRole = protectedPaths.get(path);
        if (requiredRole == null) {
            chain.doFilter(request, response); // Không cần phân quyền
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            res.sendRedirect(req.getContextPath() + "/admin/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("id");
        UserDao userDao = new UserDao();
        List<Roles> roles = userDao.getUserRole(userId);
        boolean isAdmin = roles.stream()
                .anyMatch(role -> "admin".equalsIgnoreCase(role.getRoleName()));
        boolean hasPermission = isAdmin || roles.stream()
                .anyMatch(role -> requiredRole.equalsIgnoreCase(role.getRoleName()));

        if (hasPermission) {
            chain.doFilter(request, response);
        } else {
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().println("<script>");
            res.getWriter().println("alert('Bạn không có quyền truy cập vào chức năng này!');");
            res.getWriter().println("window.history.back();");
            res.getWriter().println("</script>");
        }
    }
}

