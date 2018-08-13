package ORMTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用Object[]封装一条记录
 * 用List<Object[]>封装多条记录
 */
public class ORMTest01 {
    public static void main(String[] args) {
        Connection conn = null;
        List<Object[]> objs = new ArrayList<Object[]>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getMySqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();

            /**
             * 可以用List来封装多条记录
             * 即使ResultSet关闭了也可以读出来
             */
            while (rs.next()) {
                Object[] obj = new Object[3];
                obj[0] = rs.getString(1);
                obj[1] = rs.getDouble(2);
                obj[2] = rs.getInt(3);

                objs.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.Close(ps, conn, rs);
        }
        /**
         * 当ResultSet关闭了以后也可以读出来
         */
        for (Object[] obj : objs) {
            System.out.println("" + obj[0] + "---" + obj[1] + "---" + obj[2]);
        }

    }
}
