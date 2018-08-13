package ORMTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类
 */

public class JDBCUtil {
    static Properties pros = null;//可以帮助和处理资源文件中的信息，即db.properties中的信息

    //加载JDBCUtil类的时候调用此静态代码块
    static {
        pros = new Properties();

        try {
            /**
             * 将db.properties中的内容读出来
             * 要把db.properties文件放在src根目录下，否则会抛异常
             */
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得JDBC连接
     *
     * @return
     */
    public static Connection getMySqlConn() {
        try {
            /**
             * 可以将这些类名放在xml文件中。或者properties资源文件中
             */
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(pros.getProperty("mysqlDriver"));
            return DriverManager.getConnection(pros.getProperty("mysqlURL"), pros.getProperty("mysqlUser"), pros.getProperty("mysqlPwd"));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void Close(Statement st, Connection conn, ResultSet rs) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Close(Statement st, Connection conn) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
