package org.vulhunter.common.sqlinjection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlInjectionCommon {

  public void statementAddBatch2(HttpServletRequest request) {
    String username = request.getParameter("username");
    String sql = "SELECT u.id FROM user_t u WHERE u.user_name = '" + username + "';";
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
  }

  public void testStatementAddBatch(HttpServletRequest request){
    if(request == null){
      return;
    }
    statementAddBatch2(request);

  }

  public void sqlExecption(HttpServletRequest request) {
    String id = request.getParameter("id");
    String sql = "select * from app1_user where id = '" + id + "'";
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.execute(sql);
  }

  public void statementAddBatch1(HttpServletRequest request) {
    String username = request.getRequestURI();
    String name = username.substring(username.lastIndexOf("/") + 1, username.indexOf("."));
    String sql1 = "select * from app1_user where username = '" + name + "'";
    String sql2 = " AND pwd = '" + 123 + "';";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
  }

  public void statementAddBatch(HttpServletRequest request) {
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String valName = doSanitizer(username);
    String valPwd = doSanitizer1(pwd);
    String sql1 = "select * from app1_user where username = '" + valName + "'";
    String sql2 = " AND pwd = '" + valPwd + "';";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
  }

  public void statementAddBatch3(HttpServletRequest request){
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String valName = doSanitizer2(username);
    String valPwd = doSanitizer2(pwd);
    String sql1 = "select * from app1_user where username = '" + valName + "'";
    String sql2 = " AND pwd = '" + valPwd + "';";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
            "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
  }

  public String doInputValidator(String ruleId){
    if(ruleId != null && !"".equals(ruleId)){
      return ruleId.concat("");
    }
    return "";
  }

  public String doSanitizer2(String input){
    boolean matches = Pattern.matches("[a-zA-Z0-9_\\ \\,]+", input);
    if(matches){
      return input;
    }
    return input.concat(" ");
  }
  public String doSanitizer1(String input){
    boolean matches = Pattern.matches("^[a-zA-Z0-9]+$", input);
    if(matches){
      return input;
    }
    return input.concat(" ");
  }

  public String doSanitizer(String input) {
    Pattern compile = Pattern.compile("^[a-zA-Z0-9]+$");
    Matcher matcher = compile.matcher(input);
    if(matcher.matches()){
      return input;
    }
    return input.concat(" ");
  }

  public String statementExecute(HttpServletRequest request) {
    // String sql = getQuerySqlByRequest(request);
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String u1 = doInputValidator(username);
    String pwd1 = doInputValidator(pwd);
    String sql1 = "select * from user_t where user_name = '" + u1 + "'";
    String sql2 = " AND password = '" + pwd1 + "';";
    StringBuilder stringBuilder = new StringBuilder();
    // P1-O;O-R java.lang.StringBuilder.append(java.lang.String)
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
            "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    return ExecuteSql.executeAndReturnString(sql);
  }



  public void statementExecuteInt(HttpServletRequest request) {
    String sql = getInsertSqlByRequest(request);
    // 与contrast对比测试:contrast和agent同样对于同一请求中，堆栈信息相同的漏洞只会报一个到server端
    ExecuteSql.execute(sql, Statement.RETURN_GENERATED_KEYS);
    ExecuteSql.execute(sql);
  }

  public void statementExecuteIntArr(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    int[] arr = {};
    ExecuteSql.execute(sql, arr);
  }

  public void statementExecuteStringArr(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    String[] arr = {};
    ExecuteSql.execute(sql, arr);
  }

	/* java.sql.Statement.executeLargeUpdate since jdk1.8
	 * public void statementExecuteLargeUpdate(HttpServletRequest request) {
		String sql = getUpdateSqlByRequest(request);
		ExecuteSql.executeLargeUpdate(sql);
	}

	public void statementExecuteLargeUpdateInt(HttpServletRequest request) {
		String sql = getUpdateSqlByRequest(request);
		ExecuteSql.executeLargeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
	}

	public void statementExecuteLargeUpdateIntArr(HttpServletRequest request) {
		String sql = getUpdateSqlByRequest(request);
		int[] arr = {};
		ExecuteSql.executeLargeUpdate(sql, arr);
	}

	public void statementExecuteLargeUpdateStringArr(HttpServletRequest request) {
		String sql = getUpdateSqlByRequest(request);
		String[] arr = {};
		ExecuteSql.executeLargeUpdate(sql, arr);
	}*/

  public void statementExecuteQuery(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.executeQuery(sql);
  }

  public void statementExecuteUpdate(HttpServletRequest request) {
    String sql = getUpdateSqlByRequest(request);
    ExecuteSql.executeUpdate(sql);
  }

  public void statementExecuteUpdateInt(HttpServletRequest request) {
    String sql = getUpdateSqlByRequest(request);
    ExecuteSql.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
  }

  public void statementExecuteUpdateIntArr(HttpServletRequest request) {
    String sql = getUpdateSqlByRequest(request);
    int[] arr = {};
    ExecuteSql.executeUpdate(sql, arr);
  }

  public void statementExecuteUpdateStringArr(HttpServletRequest request) {
    String sql = getUpdateSqlByRequest(request);
    String[] arr = {};
    ExecuteSql.executeUpdate(sql, arr);
  }

  public void connectionPrepareCall(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareCall(sql);
  }

  public void connectionPrepareCallInt2(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareCall(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
  }

  public void connectionPrepareCallInt3(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareCall(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
        ResultSet.HOLD_CURSORS_OVER_COMMIT);
  }

  public void connectionPrepareStatement(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareStatement(sql);
  }

  public void connectionPrepareStatementInt(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
  }

  public void connectionPrepareStatementIntArr(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    int[] arr = {};
    ExecuteSql.prepareStatement(sql, arr);
  }

  public void connectionPrepareStatementInt2(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
  }

  public void connectionPrepareStatementInt3(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
        ResultSet.HOLD_CURSORS_OVER_COMMIT);
  }

  public void connectionPrepareStatementStringArr(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    String[] arr = {};
    ExecuteSql.prepareStatement(sql, arr);
  }

  public void createNativeQuery(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.createNativeQuery(sql);
  }

  public void createNativeQueryString(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    ExecuteSql.createNativeQuery(sql, App1User.class);
  }

  public void createNativeQueryClass(HttpServletRequest request) {
    String sql = getQuerySqlByRequest(request);
    String resultSetMapping = "UserResults";
    ExecuteSql.createNativeQuery(sql, resultSetMapping);
  }

  private String getQuerySqlByRequest(HttpServletRequest request) {
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String escapePwd = StringEscapeUtils.escapeSql(pwd);
    String sql =
        "select * from app1_user where username = '" + username + "'" + " AND pwd = '" + escapePwd
            + "';";
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    return sql;
  }

  private String getInsertSqlByRequest(HttpServletRequest request) {
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String escapePwd = StringEscapeUtils.escapeSql(pwd);
    String sql =
        "INSERT INTO app1_user (username,pwd) VALUES('" + username + "','" + escapePwd + "');";
    System.out.println(
        "-----------------------------------insert sql----------------------------------------");
    System.out.println(sql);
    return sql;
  }

  private String getUpdateSqlByRequest(HttpServletRequest request) {
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String escapePwd = StringEscapeUtils.escapeSql(pwd);
    String sql = "UPDATE app1_user SET username = 'afterUpdate' WHERE username ='" + username
        + "' AND pwd = '"
        + escapePwd + "';";
    System.out.println(
        "-----------------------------------update sql----------------------------------------");
    System.out.println(sql);
    return sql;
  }

  private String getQuerySqlByRequestOP12R(HttpServletRequest request) {
    // spread: java.lang.String.concat(java.lang.String)
    // O,P1-R
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String sql1 = "select * from app1_user where username = '" + username + "'";
    String sql2 = " AND pwd = '" + pwd + "';";
    String sql = sql1.concat(sql2);
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    return sql;
  }

  private String getQuerySqlByRequestO2O(HttpServletRequest request) {
    String username = request.getParameter("username");
    // spread: java.lang.StringBuffer.delete
    // O-O
    StringBuffer stringBuffer = new StringBuffer(username);
    StringBuffer stringBuffer1 = stringBuffer.delete(0, 1);
    String pwd = request.getParameter("pwd");
    String escapePwd = StringEscapeUtils.escapeSql(pwd);
    String sql = "select * from app1_user where username = '" + stringBuffer1.toString() + "'"
        + " AND pwd = '"
        + escapePwd + "';";
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    return sql;
  }
}
