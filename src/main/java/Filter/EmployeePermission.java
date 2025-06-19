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
        protectedPaths.put("/admin/indexAdmin.jsp", "Admin,Employee");
        protectedPaths.put("/admin/EditEmployeeController", "Admin");
        protectedPaths.put("/admin/AddEmployeeController", "Admin");
        protectedPaths.put("/admin/DeleteEmployeeController", "Admin");
        // Thêm nếu cần
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
                .anyMatch(role -> "Admin".equalsIgnoreCase(role.getRoleName()));
        if (isAdmin) {
            chain.doFilter(request, response);
            return;
        }
        List<String> requiredRoles = Arrays.asList(requiredRole.split(","));
        boolean hasPermission = roles.stream().anyMatch(userRole -> {
            String roleName = userRole.getRoleName();

            for (String required : requiredRoles) {
                required = required.trim();
                if (required.equalsIgnoreCase("Admin") && roleName.equalsIgnoreCase("Admin")) {
                    return true;
                }
                if (required.equalsIgnoreCase(roleName)) {
                    return true;
                }
                if (required.equalsIgnoreCase("Employee") && roleName.startsWith("Employee")) {
                    return true;
                }
            }
            return false;
        });

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

