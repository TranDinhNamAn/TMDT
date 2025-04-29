package Database;
import org.jdbi.v3.core.Jdbi;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/TMDT";
    private static final String USER = "root";
    private static final String PASSWORD = "Nguyenthily126.";

    private static Jdbi jdbiInstance;


    // Tạo một đối tượng Jdbi duy nhất
    public static Jdbi getJdbi() {
        if (jdbiInstance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Đăng ký driver MySQL
                jdbiInstance = Jdbi.create(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Không tìm thấy driver MySQL", e);
            }
        }
        return jdbiInstance;
    }

    public static void main(String[] args) {
        try {
            DatabaseConnection.getJdbi(); // Kiểm tra kết nối cơ sở dữ liệu
            System.out.println("Kết nối thành công với cơ sở dữ liệu!");
        } catch (Exception e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }
}