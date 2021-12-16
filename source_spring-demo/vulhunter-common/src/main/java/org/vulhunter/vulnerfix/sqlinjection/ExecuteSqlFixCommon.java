package org.vulhunter.vulnerfix.sqlinjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteSqlFixCommon {

	private static Connection conn = null;
	static {
		String url = "jdbc:mysql://db.hf.seczone.cn:3306/APPdb?user=root&password=seczone@123&autoReconnect=true";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addBatchAndCleanBatch(String sql,String name,String pwd) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			stmt.addBatch(sql);
			stmt.clearBatch();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
