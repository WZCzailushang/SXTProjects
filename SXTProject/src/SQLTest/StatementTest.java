package SQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试Statement接口的用法，执行SQL语句，测试SQL注入
 */

public class StatementTest {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接(连接对象内部其实包含了Socket对象，是一个远程的连接。比较耗时！
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            System.out.println(connection);
            statement = connection.createStatement();
            //t_jdbc表中中有四个属性值：id、username、password、regTime。其中id要设为自动递增的
            //可以向t_jdbc表中插入行，execute()为运行方法，当执行完这个方法后，sql语句才真正起效

            String sql = "INSERT INTO t_jdbc (username,password,regTime) VALUES ('dd',8888,NOW())";
            statement.execute(sql);

            String sql2 = "INSERT INTO t_jdbc (username,password,regTime) VALUES ('ee',9999,NOW())";
            statement.execute(sql2);

            String sql3 = "delete from t_jdbc where id=3";
            statement.execute(sql3);

            //测试SQL注入，本来只想删除t_jdbc表中id为4的行，但是运行过后全都删除了，会出现异常
            String str = "4 or 1=1";
            String sql4 = "delete from t_jdbc where id=" + str;
            statement.execute(sql4);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//将打开的关闭
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
