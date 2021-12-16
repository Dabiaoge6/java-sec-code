package org.vulhunter.vulnerfix.sqlinjection;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;
import org.vulhunter.common.sqlinjection.ExecuteSql;

import javax.servlet.http.HttpServletRequest;

public class SqlInjectionFixCommon {

	private String check(String str) {
		return str.replaceAll("([';]+|(--)+).*", "");
	}

	private String getQuerySqlByRequest(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		Codec mysql = new MySQLCodec(MySQLCodec.MYSQL_MODE);
		String encodeName = ESAPI.encoder().encodeForSQL(mysql, username);
		String encodePwd = ESAPI.encoder().encodeForSQL(mysql, pwd);
		String sql = "select * from app1_user where username = '" + encodeName + "'" + " AND pwd = '" + encodePwd + "';";
		System.out.println("-----------------------------------query sql----------------------------------------");
		System.out.println(sql);
		return sql;
	}

	/**
	 * PreparedStatement
	 * 
	 * @param request
	 */
	public void preparedstatementAddBatch(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String sqlStr = "select * from app1_user where username = ? and pwd = ?";
		StringBuilder stringBuilder = new StringBuilder();
		// P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
		stringBuilder.append(sqlStr);
		String sql = stringBuilder.toString();
		System.out.println("-----------------------------------query sql----------------------------------------");
		System.out.println(sql);
		ExecuteSqlFixCommon.addBatchAndCleanBatch(sql, username, pwd);
	}

	/**
	 * Input Check
	 */
	public void statementExecute(HttpServletRequest request) {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String nameCheck = check(username);
		String pwdCheck = check(pwd);
		String sql1 = "select * from app1_user where username = '" + nameCheck + "'";
		String sql2 = " AND pwd = '" + pwdCheck + "';";
		StringBuilder stringBuilder = new StringBuilder();
		// P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
		stringBuilder.append(sql1);
		stringBuilder.append(sql2);
		String sql = stringBuilder.toString();
		System.out.println("-----------------------------------query sql----------------------------------------");
		System.out.println(sql);
		ExecuteSql.execute(sql);
	}

	/**
	 * ESAPI 库函数
	 */
	public void statementExecuteIntArr(HttpServletRequest request) {
		String sql = getQuerySqlByRequest(request);
		int[] arr = {};
		ExecuteSql.execute(sql, arr);
	}
}
