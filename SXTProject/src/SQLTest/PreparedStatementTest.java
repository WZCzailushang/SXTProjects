package SQLTest;

import java.sql.*;

/**
 * 测试PreparedStatement的用法,执行sql语句
 */
public class PreparedStatementTest {
    public static void main(String[] args) {
        try {
            /**
             * 加载类
             */
            Class.forName("com.mysql.jdbc.Driver");
            /**
             * 创建连接
             */
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            String str = "insert into t_jdbc (username,password,regTime) values (?,?,?)";//?为占位符，先不赋值，可以后面再赋值
            /**
             * 获得PreparedStatement执行sql语句
             */
            PreparedStatement ps = connection.prepareStatement(str);
//            ps.setString(1,"WZC");//设置第一个占位符？所代表的String值
//            ps.setString(2,"123456");//设置第二个占位符?所代表的String值

            ps.setObject(1, "LY");//使用setObject()方法可以不管占位符所代表的类型
            ps.setObject(2, "23456");
            ps.setObject(3, new Date(System.currentTimeMillis()));//此处的new Date()要用java.sql.Date()

            ps.execute();//执行语句


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
