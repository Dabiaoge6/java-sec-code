package org.vulhunter.common.sqlinjection;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ConnectionWrapper implements Connection {

  private Connection conn;

  public ConnectionWrapper(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Statement createStatement() throws SQLException {
    return conn.createStatement();
  }

  @Override
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    return conn.prepareStatement(sql);
  }

  @Override
  public CallableStatement prepareCall(String sql) throws SQLException {
    return conn.prepareCall(sql);
  }

  @Override
  public String nativeSQL(String sql) throws SQLException {
    return conn.nativeSQL(sql);
  }

  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {
  }

  @Override
  public boolean getAutoCommit() throws SQLException {
    return conn.getAutoCommit();
  }

  @Override
  public void commit() throws SQLException {

  }

  @Override
  public void rollback() throws SQLException {

  }

  @Override
  public void close() throws SQLException {
    conn.close();
  }

  @Override
  public boolean isClosed() throws SQLException {
    return false;
  }

  @Override
  public DatabaseMetaData getMetaData() throws SQLException {
    return null;
  }

  @Override
  public void setReadOnly(boolean readOnly) throws SQLException {

  }

  @Override
  public boolean isReadOnly() throws SQLException {
    return false;
  }

  @Override
  public void setCatalog(String catalog) throws SQLException {

  }

  @Override
  public String getCatalog() throws SQLException {
    return null;
  }

  @Override
  public void setTransactionIsolation(int level) throws SQLException {

  }

  @Override
  public int getTransactionIsolation() throws SQLException {
    return 0;
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    return null;
  }

  @Override
  public void clearWarnings() throws SQLException {

  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency)
      throws SQLException {
    return conn.createStatement(resultSetType,resultSetConcurrency);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    return conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
      throws SQLException {
    return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
  }

  @Override
  public Map<String, Class<?>> getTypeMap() throws SQLException {
    return null;
  }

  @Override
  public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

  }

  @Override
  public void setHoldability(int holdability) throws SQLException {

  }

  @Override
  public int getHoldability() throws SQLException {
    return 0;
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return null;
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return conn.isWrapperFor(iface);
  }

  @Override
  public Savepoint setSavepoint() {
    return null;
  }

  @Override
  public Savepoint setSavepoint(String name) throws SQLException {
    return null;
  }

  @Override
  public void rollback(Savepoint savepoint) throws SQLException {

  }

  @Override
  public void releaseSavepoint(Savepoint savepoint) throws SQLException {

  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    return conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
      int resultSetHoldability) throws SQLException {
    return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
    return conn.prepareStatement(sql, autoGeneratedKeys);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
    return conn.prepareStatement(sql, columnIndexes);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
    return conn.prepareStatement(sql, columnNames);
  }

  @Override
  public Clob createClob() throws SQLException {
    return conn.createClob();
  }

  @Override
  public Blob createBlob() throws SQLException {
    return conn.createBlob();
  }

  @Override
  public NClob createNClob() throws SQLException {
    return conn.createNClob();
  }

  @Override
  public SQLXML createSQLXML() throws SQLException {
    return conn.createSQLXML();
  }

  @Override
  public boolean isValid(int timeout) throws SQLException {
    return conn.isValid(timeout);
  }

  @Override
  public void setClientInfo(String name, String value) throws SQLClientInfoException {

  }

  @Override
  public void setClientInfo(Properties properties) throws SQLClientInfoException {

  }

  @Override
  public String getClientInfo(String name) throws SQLException {
    return null;
  }

  @Override
  public Properties getClientInfo() throws SQLException {
    return null;
  }

  @Override
  public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    return conn.createArrayOf(typeName, elements);
  }

  @Override
  public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    return conn.createStruct(typeName, attributes);
  }

  public void setSchema(String schema) throws SQLException {

  }

  public String getSchema() throws SQLException {
    return null;
  }

  public void abort(Executor executor) throws SQLException {

  }

  public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

  }

  public int getNetworkTimeout() throws SQLException {
    return 0;
  }


}
