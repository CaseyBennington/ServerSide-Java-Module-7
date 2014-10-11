package data;

import java.sql.*;

public class DBUtil {

    public static Connection connect() {
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/vacation";

        try { // load the driver 
            Class.forName(driver).newInstance();
        } catch (Exception e) { // problem loading driver, class not exist?
            e.printStackTrace();

        }
        try {
            connection = DriverManager.getConnection(url, "itp246", "itp246");
            System.out.println("Connection successful!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}