package Controller;

import DAO.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;
import org.json.JSONObject;
import Utils.HttpRequestUtil;

import java.io.IOException;

@WebServlet("/FacebookLoginController")
public class FacebookLoginController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            // Nếu không có mã xác thực, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Authentication failed");
            return;
        }

        // Lấy access token từ Facebook
        String accessTokenUrl = "https://graph.facebook.com/v10.0/oauth/access_token?"
                + "client_id=1369237884391739"
                + "&redirect_uri=http://localhost:8081/Gradle___com_example___TTWeb_1_0_SNAPSHOT_war/FacebookLoginController"
                + "&client_secret=8622822c87cde7470189eb15e98b6e14"
                + "&code=" + code;

        String accessTokenResponse = HttpRequestUtil.sendGetRequest(accessTokenUrl);
        if (accessTokenResponse == null || accessTokenResponse.isEmpty()) {
            // Nếu không thể lấy access token, chuyển hướng về trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Failed to retrieve access token");
            return;
        }

        // Parse JSON để lấy giá trị access token
        JSONObject tokenJson = new JSONObject(accessTokenResponse);
        String accessToken = tokenJson.optString("access_token");
        if (accessToken == null || accessToken.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Invalid access token");
            return;
        }

        // Lấy thông tin user từ Facebook Graph API
        String userInfoUrl = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + accessToken;
        String userInfoResponse = HttpRequestUtil.sendGetRequest(userInfoUrl);

        if (userInfoResponse == null || userInfoResponse.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Failed to retrieve user info");
            return;
        }

        // Parse JSON để lấy thông tin user
        JSONObject userJson = new JSONObject(userInfoResponse);
        String email = userJson.optString("email");
        String name = userJson.optString("name");
        String profilePicture = userJson.optJSONObject("picture").optJSONObject("data").optString("url");

        if (email == null || email.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Email not found");
            return;
        }

        // Kiểm tra user trong database
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            user = new User(name, email, User.Provider.FACEBOOK, profilePicture);
            user.setEpassword("default_password"); // Đặt mật khẩu mặc định
            boolean isRegistered = userDao.registerUser(user);
            if (!isRegistered) {
                response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=Failed to register user");
                return;
            }
        }

        // Lưu user vào session và chuyển hướng
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}