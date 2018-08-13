package ORMTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用 Map<String,Object[]> 封装一条记录
 * 用 List<Map> 封装多条记录
 */
public class ORMTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getMySqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();

            /**
             * 可以用List来封装多条记录,每条记录用Map封装
             * 即使ResultSet关闭了也可以读出来
             */
            while (rs.next()) {
//                Object[] obj = new Object[3];
//                obj[0] = rs.getString(1);
//                obj[1] = rs.getDouble(2);
//                obj[2] = rs.getInt(3);
                Map<String, Object> row = new HashMap<String, Object>();
                row.put("empname", rs.getString(1));
                row.put("salary", rs.getDouble(2));
                row.put("age", rs.getInt(3));
                objs.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.Close(ps, conn, rs);
        }


        /**
         * 当ResultSet关闭了以后也可以读出来
         * 遍历Map
         */
        for (Map<String, Object> obj : objs) {
            for (String key : obj.keySet()) {
                System.out.print(key + "--" + obj.get(key) + " ");
            }
        }
    }
}
