package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projectweb";
            String username = "root";
            String password = "";
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver không được tìm thấy", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            if (con != null) {
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
                System.out.println("Connection: " + con);
                con.close();
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
    }
}
