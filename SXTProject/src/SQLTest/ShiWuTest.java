package SQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用来测试事务
 */

public class ShiWuTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            connection.setAutoCommit(false);//，这里设为手动提交事务。jdbc中默认为true，自动提交事务
            /**
             * 下面的两条插入语句同属一个事务，要么同时执行成功，要么同时执行失败
             * 如果某一个执行失败，那插入的表就回滚到最初的状态
             */

            String sql = "insert into t_jdbc (username,password) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "WZC");
            preparedStatement.setObject(2, "123456");
            preparedStatement.execute();

            String sql2 = "insert into t_jdbc (username,password) values(?,?)";
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setObject(1, "李泽");
            preparedStatement2.setObject(2, "123456");
            preparedStatement2.execute();

            connection.commit();//提交事务

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();

            /**
             * 回滚：当发生异常时，将数据表的状态设为最初的状态
             */
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
