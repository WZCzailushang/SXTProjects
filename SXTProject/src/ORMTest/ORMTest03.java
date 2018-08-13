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
 * 测试用 Emp(javabean) 封装一条记录
 * 用 List<Emp> 封装多条记录
 * 企业里用的最多的方式
 */
public class ORMTest03 {
    public static void main(String[] args) {
        Connection conn = null;
        List<Emp> objs = new ArrayList<Emp>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getMySqlConn();
            ps = conn.prepareStatement("select empname,salary,age from emp where id = ?");
            ps.setObject(1, 1);
            rs = ps.executeQuery();

            /**
             * 可以用List来封装多条记录,每条记录用Emp封装
             * 即使ResultSet关闭了也可以读出来
             */
            while (rs.next()) {
                Emp emp = new Emp(rs.getString(1), rs.getDouble(2), rs.getInt(3));
                objs.add(emp);
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
        for (Emp emp : objs) {
            System.out.println(emp.getEmpname() + "---" + emp.getSalary() + "---" + emp.getAge());
        }
    }
}
