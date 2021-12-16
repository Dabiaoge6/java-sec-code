package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("unsafeDbPwdStorge")
public class UnsafeDbPasswordStorgeController {

  private Connection loadConn1() {
    Connection connection = null;
    try {
      InputStream fileInputStream = this.getClass().getResourceAsStream("/config.properties");
      Properties properties = new Properties();
      properties.load(fileInputStream);
      String driverName = properties.getProperty("driver");
      String url = properties.getProperty("url");
      String username = properties.getProperty("username");
      String pwd = properties.getProperty("password");
      Class.forName(driverName);
      connection = DriverManager.getConnection(url, username, pwd);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return connection;
  }

  @RequestMapping("/unsafeDbPwdStorge.do")
  public void unsafeDbPwdStorge(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PreparedStatement stmt;
    try {
      Connection connection = loadConn1();
      if (connection == null) {
        PrintWriter writer = response.getWriter();
        writer.println("数据库连接失败");
        return;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    PrintWriter writer = response.getWriter();
    writer.println("上报漏洞：1.不安全的数据库密码存储 2.弱密码数据库连接 3.不安全的哈希算法");
  }
}
