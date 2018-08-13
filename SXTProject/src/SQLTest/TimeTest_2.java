package SQLTest;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 测试java.sql.Date、Time、Timestamp，取出数据表中指定时间段的区间
 */

public class TimeTest_2 {

    /**
     * 将传进来的字符串时间转换成long型数据
     *
     * @param dateStr
     * @return
     */
    public static long str2Date(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            return dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;

        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost:3306/testjdbc
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");


            /**
             * 用来选择类型为Date的区间
             */
//            String sql = "select * from t_jdbc where regTime<? and regTime>? order by regTime";
//            ps = connection.prepareStatement(sql);

            /**
             * 在此处设置起始时间,Date,只显示年、月、日
             */
//            java.sql.Date start = new java.sql.Date(str2Date("2018-07-30 10:30:30"));
//            java.sql.Date end = new java.sql.Date(str2Date("2018-08-02 17:30:30"));

            /**
             * 选择类型为Timestamp的区间，显示年、月、日、时、分、秒
             */

            String sql = "select * from t_jdbc where loginTime<? and loginTime>? order by loginTime";
            ps = connection.prepareStatement(sql);
            /**
             * 在此设置选择的起始时间和结束时间
             */

            java.sql.Timestamp start = new java.sql.Timestamp(str2Date("2018-07-30 21:30:30"));
            java.sql.Timestamp end = new java.sql.Timestamp(str2Date("2018-08-02 21:30:30"));


            /**
             * 将起始时间带入到选择条件中
             */
            ps.setObject(1, end);
            ps.setObject(2, start);

            /**
             * 得到符合条件的结果集并打印
             */
            rs = ps.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getInt("id") + "---" + rs.getString("username") + "---" + rs.getDate("regTime"));

                /**
                 * 可以用index来获得元素，也可以用名称来获得元素
                 */
                System.out.println(rs.getInt("id") + "---" + rs.getString("username") + "---" + rs.getTimestamp("loginTime"));

            }
//


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
