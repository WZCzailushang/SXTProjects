package SQLTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class JDBCTest {



    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getMySqlConn();
            System.out.println(conn==null);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            JDBCUtil.Close(ps, conn);
        }


    }
}
