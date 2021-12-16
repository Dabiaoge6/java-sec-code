package org.vulhunter.common.sqlinjection;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.vulhunter.util.JDBCUtil;

public class MyDataSource implements DataSource {

  private List<Connection> connList = new ArrayList<Connection>();

  public MyDataSource() {
    for(int i=0;i<3;i++){
      connList.add(JDBCUtil.getConn());
    }
  }

  @Override
  public Connection getConnection() throws SQLException {
    Connection conn = connList.remove(0);
    MyConnectionWrapper connWrapper = new MyConnectionWrapper(conn,connList);
    return connWrapper;
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    return null;
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return null;
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return false;
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return null;
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {

  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {

  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return 0;
  }

  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return null;
  }

}
