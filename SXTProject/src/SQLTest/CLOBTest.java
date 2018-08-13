package SQLTest;

import java.io.*;
import java.sql.*;

/**
 * 测试CLOB，将文本写入到数据库中
 */

public class CLOBTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            /**
             * 将文本写入到数据库中的CLOB字段
             */

            String sql = "insert into t_jdbc2 (username,info) values (?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1, "WZC");
            /**
             * setClob:第二个参数是流
             * 将文本文件内容输入到数据库中
             */
//            preparedStatement.setClob(2, new FileReader(new File("H:\\ideaProjects\\SXTProject\\src\\SQLTest\\a.txt")));

            /**
             * 也可以将字符串直接写入
             * 将程序中的字符串直接输入到数据库中的CLOB字段中
             */

            preparedStatement.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("wwwwzzzccc".getBytes()))));

            preparedStatement.executeUpdate();


            /**
             * 将数据库中的CLOB字段取出来
             */
            preparedStatement = connection.prepareStatement("select * from t_jdbc2 where id = ?");
            preparedStatement.setObject(1, 3);
            ResultSet resultSet = preparedStatement.executeQuery();
            int temp = 0;
            while (resultSet.next()) {
                Clob info = resultSet.getClob("info");
                Reader reader = info.getCharacterStream();
                while ((temp = reader.read()) != -1) {
                    System.out.print((char) temp);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
