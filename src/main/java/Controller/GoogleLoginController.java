package Controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import DAO.UserDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;

import java.io.IOException;

@WebServlet("/GoogleLoginController")
public class GoogleLoginController extends HttpServlet {
    private final UserDao userDao = new UserDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                new JacksonFactory(),
                "707845209669-kvivpovf381f31e821p2i7i6sn9126or.apps.googleusercontent.com",
                "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",
                code,
                "http://localhost:8081/Gradle___com_example___TTWeb_1_0_SNAPSHOT_war/GoogleLoginController"
        ).execute();

        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String email = payload.getEmail();
        String name = (String) payload.get("name");

        // Kiểm tra nếu tài khoản chưa có trong database, tạo mới
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            user = new User(name, email, User.Provider.GOOGLE);
            user.setEpassword("default_password"); // Hoặc bất kỳ giá trị nào bạn muốn
            userDao.registerUser(user);
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}