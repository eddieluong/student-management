import java.sql.Connection;
import java.sql.SQLException;
import config.DatabaseConnection;

public class TestConnection {
  public static void main(String[] args) {
    try (Connection conn = DatabaseConnection.getConnection()) {
      System.out.println("✅ Kết nối thành công!");
    } catch (SQLException e) {
      System.out.println("❌ Kết nối thất bại: " + e.getMessage());
    }
  }
}
