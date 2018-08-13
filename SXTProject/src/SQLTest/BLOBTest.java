package SQLTest;

import java.io.*;
import java.sql.*;

/**
 * 测试BLOB，将二进制文件写入到数据库中
 */

public class BLOBTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement ps = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "123456");
            String sql = "insert into t_jdbc2 (username,headImg) values (?,?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "WZC");
            /**
             * 将图片通过流的方式插入到数据库中
             */
            preparedStatement.setBlob(2, new FileInputStream("H:\\ideaProjects\\SXTProject\\src\\SQLTest\\mole1_1.jpg"));
            preparedStatement.execute();

            /**
             * 将数据库中的图片输出到硬盘中
             */
            ps = connection.prepareStatement("select * from t_jdbc2 where id=?");
            ps.setObject(1, 4);
            ResultSet resultSet = ps.executeQuery();
            int temp = 0;
            while (resultSet.next()) {
                Blob blob = resultSet.getBlob("headImg");
                is = blob.getBinaryStream();
                os = new FileOutputStream("H:\\ideaProjects\\SXTProject\\src\\SQLTest\\a.jpg");
                while ((temp = is.read()) != -1) {
                    os.write(temp);
                }

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }
}
