package SQLTest;

import java.sql.*;
import java.util.Random;

/**
 * 测试java.sql.Date、Time、Timestamp
 */

public class TimeTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost:3306/testjdbc
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");


            String sql = "insert into t_jdbc (username,password,regTime,loginTime) values(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < 1000; i++) {

                ps.setObject(1, "WZC" + i);
                ps.setObject(2, 131313);

                int i1 = 100000000 + new Random().nextInt(1000000000);

                java.sql.Date date = new java.sql.Date(System.currentTimeMillis() - i1);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis() - i1);

                ps.setObject(3, date);
                ps.setObject(4, timestamp);

                ps.execute();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
