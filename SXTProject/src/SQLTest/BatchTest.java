package SQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 利用Batch进行批处理操作，比如一次插入20000条数据
 * 可以进行批量的添加删除等操作
 */
public class BatchTest {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //jdbc:mysql://localhost:3306/testjdbc
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            stmt = connection.createStatement();

            connection.setAutoCommit(false);//设为手动提交事务
            //进行批处理操作，一次提交
            for (int i = 0; i < 20000; i++) {
                stmt.addBatch("insert into t_jdbc (username,password,regTime) values ('WZC" + i + "',66666,now())");
            }

            stmt.executeBatch();//执行批处理操作
            connection.commit();//提交事务
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
