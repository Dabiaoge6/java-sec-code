/*
 *
 * FileName：SqlHandle
 * Description：SqlHandle
 * Version：1.0
 * Modified By：
 * Author: mapf
 * Date:2020/12/6 22:11
 */

package org.spring.demo.controller.sensitivedata.sql;

import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;
import org.vulhunter.util.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author mapf
 * @description
 * @date 2020/12/6 22:11
 */
public class SqlHandle {
    static String driverClass = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://db.hf.seczone.cn:3306/APPdb";
    static String name = "root";
    static String password = "seczone@123";
    static Connection conn;

    public static RestApiResponseVo sqlHandle(String param){
        String sql = String.format("select * from app1_user where username = '%s'",param);
        try{
            Statement stmt = null;
            Connection conn = null;
            try {
                conn =getConn();
                stmt = conn.createStatement();
                stmt.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                JDBCUtil.release(stmt, conn);
            }
        }catch (Exception e){
        }
        return new RestApiResponseVo(200, "sql="+sql);
    }

    private static Connection getConn() {
        Connection conn = null;
        if (null != conn){
            return conn;
        }
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            System.out.println("创建连接对象失败");
        }

        return conn;
    }
}
