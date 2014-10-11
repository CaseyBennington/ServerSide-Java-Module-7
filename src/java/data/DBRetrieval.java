package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBRetrieval {

    public static HashMap<String, Integer> getData() {

        Connection connection = DBUtil.connect();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String query =
                "Select * from bid";
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String destination = rs.getString(1);
                int bids = rs.getInt(2);
                System.out.println(destination + "   " + bids);
                map.put(destination, bids);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        return map;
    }
}