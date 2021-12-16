package org.spring.demo.controller.vulnercontroller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.service.demo.service.UserService;
import org.spring.demo.util.RSAUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vulhunter.common.sqlinjection.ExecuteSql;
import org.vulhunter.common.sqlinjection.SqlInjectionCommon;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("sqlInjection")
public class SqlInjectionController {

  @Resource
  UserService userService;

  @RequestMapping(value = "/testEncryption1.do", headers = {
      "content-type=application/json;charset=UTF-8"})
  public void testEncryption1(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    String requestBody = "";
    String decryptPostBody = "";
    byte[] cache = new byte[1024];
    BufferedReader br = request.getReader();
    String str = "";
    while ((str = br.readLine()) != null) {
      requestBody += str;
    }

    if (requestBody == null || "".equals(requestBody)) {
      return;
    }
    try {
      decryptPostBody = RSAUtils.decrypt(requestBody);
      ObjectMapper objectMapper = new ObjectMapper();
      /*org.spring.demo.User user = objectMapper.readValue(decryptPostBody, org.spring.demo.User.class);
      String name = user.getName();
      String pwd = user.getPwd();*/
      JsonNode jsonNode = objectMapper.readTree(decryptPostBody);
      String name = jsonNode.get("name").asText();
      String pwd = jsonNode.get("pwd").asText();
      String sql1 = "select * from app1_user where username = '" + name + "'";
      String sql2 = " AND pwd = '" + pwd + "';";
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(sql1);
      stringBuilder.append(sql2);
      String sql = stringBuilder.toString();
      System.out.println(
          "-----------------------------------query sql----------------------------------------");
      System.out.println(sql);
      ExecuteSql.addBatchAndCleanBatch(sql);
      PrintWriter out = responese.getWriter();
      out.println("test Statement.addBatch");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        br.close();
      }
    }
  }

  @RequestMapping(value = "/noEncryption2.do", headers = {
      "content-type=application/json;charset=UTF-8"})
  public void testEncryption2(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    String requestBody = "";
    BufferedReader br = request.getReader();
    String str = "";
    while ((str = br.readLine()) != null) {
      requestBody += str;
    }

    if (requestBody == null || "".equals(requestBody)) {
      return;
    }
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      /*vulhunter 可以跟踪的函数
      org.spring.demo.User user = objectMapper.readValue(requestBody, org.spring.demo.User.class);
      String name = user.getName();
      String pwd = user.getPwd();*/
      //objectMapper.readTree是seeker可以跟踪的函数
      JsonNode jsonNode = objectMapper.readTree(requestBody);
      String name = jsonNode.get("name").asText();
      String pwd = jsonNode.get("pwd").asText();
      /*String sql1 = "select * from app1_user where username = '" + name + "'";
      String sql2 = " AND pwd = '" + pwd + "';";
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(sql1);
      stringBuilder.append(sql2);
      String sql = stringBuilder.toString();
      System.out.println(
          "-----------------------------------query sql----------------------------------------");
      System.out.println(sql);
      */
      String sql_1 =
          "select * from app1_user where username = 'hujj'" + " AND pwd = '" + pwd + "';";
      String sql_2 =
          "select * from app1_user where username = '"+name+"'" + " AND pwd = 'hujj';";
      ExecuteSql.addBatchAndCleanBatch(sql_1);
      ExecuteSql.addBatchAndCleanBatch(sql_2);
      PrintWriter out = responese.getWriter();
      out.println("test Statement.addBatch");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        br.close();
      }
    }
  }

  @RequestMapping("/testEncryption.do")
  public void testEncryption(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    String passwd = request.getParameter("passwd");
    String name = request.getParameter("name");
    String decryptDesPasswd = null;
    String decryptDesName = null;
    try {
      decryptDesPasswd = RSAUtils.decrypt(passwd);
      decryptDesName = RSAUtils.decrypt(name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String sql1 = "select * from app1_user where username = '" + decryptDesName + "'";
    String sql2 = " AND pwd = '" + decryptDesPasswd + "';";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  @RequestMapping("/encryption.do")
  public void testInputValidate(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    String passwd = request.getParameter("passwd");
    String name = request.getParameter("name");
    String decryptDesPasswd = null;
    try {
      decryptDesPasswd = RSAUtils.decrypt(passwd);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String sql1 = "select * from app1_user where username = '" + name + "'";
    String sql2 = " AND pwd = '" + decryptDesPasswd + "';";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(sql1);
    stringBuilder.append(sql2);
    String sql = stringBuilder.toString();
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  @RequestMapping("/sql000.do")
  public void statementAddBatch1(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementAddBatch1(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  @RequestMapping("/sqlerror.do")
  public void statementAddBatchExecption(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.sqlExecption(request);
  }

  @RequestMapping("/sql001.do")
  public void statementAddBatch(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementAddBatch(request);
    /*SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
    sqlInjectionTestService.preparedstatementAddBatch(request);*/
    PrintWriter out = responese.getWriter();
    out.println("上报漏洞：SQL;注入跨站请求伪造;不安全的数据库密码存储;弱密码数据库连接");
  }

  @RequestMapping("/sql002.do")
  @ResponseBody
  public String statementExecute(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    String username = request.getParameter("username");
    userService.selectId(username);

    return sqlInjectionTestService.statementExecute(request);
  }

  @RequestMapping("/sql003.do")
  public void statementExecuteInt(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteInt(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.execute(sql,int)");
  }

  @RequestMapping("/sql004.do")
  public void statementExecuteIntArr(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteIntArr(request);
    /*SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
    sqlInjectionTestService.statementExecuteIntArr(request);*/
    PrintWriter out = responese.getWriter();
    out.println("test Statement.execute(sql,intArr)");
  }

  @RequestMapping("/sql005.do")
  public void statementExecuteStringArr(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteStringArr(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.execute(sql,StringArr)");
  }

    /* java.sql.Statement.executeLargeUpdate since jdk1.8
     * @RequestMapping("/statementExecuteLargeUpdate-sql.do")
    public void statementExecuteLargeUpdate(HttpServletRequest request) {
    	SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
        sqlInjectionTestService.statementExecuteLargeUpdate(request);
    }

    @RequestMapping("/statementExecuteLargeUpdate-sql-int.do")
    public void statementExecuteLargeUpdateInt(HttpServletRequest request) {
    	SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
        sqlInjectionTestService.statementExecuteLargeUpdateInt(request);
    }

    @RequestMapping("/statementExecuteLargeUpdate-sql-intArr.do")
    public void statementExecuteLargeUpdateIntArr(HttpServletRequest request) {
    	SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
        sqlInjectionTestService.statementExecuteLargeUpdateIntArr(request);
    }

    @RequestMapping("/statementExecuteLargeUpdate-sql-StringArr.do")
    public void statementExecuteLargeUpdateStringArr(HttpServletRequest request) {
    	SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
        sqlInjectionTestService.statementExecuteLargeUpdateStringArr(request);
    }*/

  @RequestMapping("/sql006.do")
  public void statementExecuteQuery(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteQuery(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.executeQuery(sql)");
  }

  @RequestMapping("/sql007.do")
  public void statementExecuteUpdate(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteUpdate(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.executeUpdate(sql)");
  }

  @RequestMapping("/sql008.do")
  public void statementExecuteUpdateInt(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteUpdateInt(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.executeUpdate(sql,int)");
  }

  @RequestMapping("/sql009.do")
  public void statementExecuteUpdateIntArr(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteUpdateIntArr(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.executeUpdate(sql,intArr)");
  }

  @RequestMapping("/sql010.do")
  public void statementExecuteUpdateStringArr(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementExecuteUpdateStringArr(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.executeUpdate(sql,StringArr)");
  }

  @RequestMapping("/sql011.do")
  public void connectionPrepareCall(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareCall(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareCall(string)");
  }

  @RequestMapping("/sql012.do")
  public void connectionPrepareCallInt2(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareCallInt2(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareCall(string,int,int)");
  }

  @RequestMapping("/sql013.do")
  public void connectionPrepareCallInt3(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareCallInt3(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareCall(string,int,int,int)");
  }

  @RequestMapping("/sql014.do")
  public void connectionPrepareStatement(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatement(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string)");
  }

  @RequestMapping("/sql015.do")
  public void connectionPrepareStatementInt(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatementInt(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string,int)");
  }

  @RequestMapping("/sql016.do")
  public void connectionPrepareStatementIntArr(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatementIntArr(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string,int[])");
  }

  @RequestMapping("/sql017.do")
  public void connectionPrepareStatementInt2(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatementInt2(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string,int,int)");
  }

  @RequestMapping("/sql018.do")
  public void connectionPrepareStatementInt3(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatementInt3(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string,int,int,int)");
  }

  @RequestMapping("/sql019.do")
  public void connectionPrepareStatementStringArr(HttpServletRequest request,
      HttpServletResponse responese) throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.connectionPrepareStatementStringArr(request);
    PrintWriter out = responese.getWriter();
    out.println("test Connection.prepareStatement(string,String[])");
  }

  @RequestMapping("/sql020.do")
  public void createNativeQuery(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.createNativeQuery(request);
    PrintWriter out = responese.getWriter();
    out.println("test EntityManager.createNativeQuery(string)");
  }

  @RequestMapping("/sql021.do")
  public void createNativeQueryString(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.createNativeQueryString(request);
    PrintWriter out = responese.getWriter();
    out.println("test EntityManager.createNativeQuery(String,String)");
  }


  @RequestMapping("/sql022.do")
  public void createNativeQueryClass(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.createNativeQueryClass(request);
    PrintWriter out = responese.getWriter();
    out.println("test EntityManager.createNativeQuery(String,Class)");
  }

  @RequestMapping("/sql023.do")
  public void statementAddBatch3(HttpServletRequest request, HttpServletResponse responese)
          throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementAddBatch3(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch3");
  }
}
