package SQLTest;

import java.sql.*;

/**
 * 测试ResultSet
 */
public class ResultSetTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");

            String sql = "select id,username,password from t_jdbc where id>?";//占位符，设置选取id大于?

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, 2);//设置从t_jdbc中选取id值大于2的id、username、password

            /**
             * 本来是execute()执行sql语句操作,现在改为executeQuery(),返回结果集ResultSet
             * 通过ResultSet选择上述符合条件的t_jdbc表中的行，并通过
             */
//            preparedStatement.execute();
            resultSet = preparedStatement.executeQuery();//返回结果集ResultSet

            while (resultSet.next()) {//此方法返回为boolean值，用来代表返回的符合条件的结果集中是否还有下一个元素
                /**
                 * getInt(1)：获取第一列的int值,即id
                 * getString(2)：获取第二列的String值,即username
                 * getString(3)：获取第三列的String值，即password
                 */
                System.out.println(resultSet.getInt(1) + "----" + resultSet.getString(2) + "----" + resultSet.getString(3));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {   //分别关闭resultSet、preparedStatement、connection
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


