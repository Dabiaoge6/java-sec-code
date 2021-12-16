package org.vulhunter.util;

import java.sql.*;

public class JDBCUtil {
  static String driverClass = "org.h2.Driver";
  static String url = "jdbc:h2:mem:h2db;INIT=runscript from 'classpath:schema.sql';MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
  static String name = "sa";
  static String password = "seczone@123";
  static Connection conn;

  /**
   * @Title: getConn
   * @Description: TODO(创建一个连接对象)
   * @return
   * @return Connection
   */
  public static Connection getConn() {
    if (null != conn){
      return conn;
    }
    try {
      Class.forName(driverClass);
      conn = DriverManager.getConnection(url, name, password);
//      String dataSql = JDBCUtil.class.getResource("schema.sql" ).getPath();
//      Statement st = conn.createStatement();
//      st.execute( "runscript from '" + dataSql + "'");
//      st.close();
      Statement st = conn.createStatement();
      st.execute( "INSERT INTO user_t VALUES('65d86e3a95a945f9bf56eed2c340e9c2','liu','123',1);");
      st.close();
    } catch (SQLException e) {
      System.out.println("创建连接对象失败");
    } catch (ClassNotFoundException e) {
      System.out.println("注册失败");
    }

    return conn;
  }

  /**
   * @Title: release
   * @Description: TODO(关闭增删改的对象)
   * @param stmt
   * @param conn
   * @return void
   */

  public static void release(Statement stmt,Connection conn) {

    closeStmt(stmt);
    closeConn(conn);

  }

  /**
   * @Title: release
   * @Description: TODO(关闭查找的对象)
   * @param rs
   * @param stmt
   * @param conn
   * @return void
   */

  public static void release(ResultSet rs,Statement stmt,Connection conn) {
    closeRs(rs);
    closeStmt(stmt);
    closeConn(conn);
  }


  /**
   * @Title: release
   * @Description: TODO(释放并归还连接对象)
   * @param stmt
   * @return void
   */

  public static void release(Statement stmt) {

    closeStmt(stmt);
  }
  /**
   * @Title: release
   * @Description: TODO(释放并归还连接对象)
   * @param rs
   * @param stmt
   * @return void
   */

  public static void release(ResultSet rs,Statement stmt) {
    closeRs(rs);
    closeStmt(stmt);
  }

  /**
   * @Title: closeRs
   * @Description: TODO(关闭结果集)
   * @param rs
   * @return void
   */
  private static void closeRs(ResultSet rs) {
    try {
      if(rs!=null) {
        rs.close();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      rs = null;
    }
  }

  /**
   * @Title: closeStmt
   * @Description: TODO(关闭stmt)
   * @param stmt
   * @return void
   */
  private static void closeStmt(Statement stmt) {
    try {
      if(stmt != null) {
        stmt.close();
      }

    } catch (SQLException e) {

      e.printStackTrace();
    }finally {
      stmt = null;
    }
  }
  /**
   * @Title: closeConn
   * @Description: TODO(关闭连接对象)
   * @param conn
   * @return void
   */
  public static void closeConn(Connection conn) {

    try {
      if(conn != null) {
        conn.close();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      conn = null;
    }
  }

}
