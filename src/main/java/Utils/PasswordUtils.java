package Utils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }
        public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }



    public static void main(String[] args) {
        String plainPassword = "1234567890";
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt(10)); // 10 là số vòng mã hóa
        System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);
    }

}
