package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.sqlinjection.ExecuteSql;

@Controller
@RequestMapping("cookieInjection")
public class CookieInjectionController {

  @RequestMapping("/cookie001.do")
  public void cookie001(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String pwd = request.getParameter("test");
//    String pwd = request.getHeader("pwd");
    Cookie cookie = new Cookie("test", pwd);
    response.addCookie(cookie);
  }

  @RequestMapping("/cookie002.do")
  public void cookie002(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String pwd = request.getParameter("test");
    response.setHeader("Set-Cookie",pwd);
  }

  @RequestMapping("/cookie.do")
  public void cookie(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String pwd = request.getParameter("test");
    if (pwd.contains("\r\n")) {
      System.out.println("request contains rn");
      String[] h1 = pwd.split("\r\n");
      System.out.println("1===============  request contains rn and prepare verify : " + pwd);
      String[] h2 = h1[1].split(":");
      System.out.println("1.3===============  get cookie value is true" + (null != h2));
      System.out.println("1.5===============  get cookie value by split" + h2[0].trim() + "," + h2[1].trim());
      Cookie cookie1 = new Cookie(h2[0].trim(), h2[1].trim());
      System.out.println("2===============  request contains rn and new verify");
      response.addCookie(cookie1);
      System.out.println("3===============  request contains rn and add verify");
      System.out.println("get a rn cookie" + cookie1.getName() + cookie1.getValue());
    } else {
      System.out.println("get a no verify cookie");
      Cookie cookie = new Cookie("test", pwd);
      response.addCookie(cookie);
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      response.setHeader("Set-Cookie", "secure=false ;HttpOnly");
      response.getWriter().print("上报漏洞：cookie注入漏洞");
    }
  }

  @RequestMapping("/injection1.do")
  public void injection1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Cookie[] reqCookie = request.getCookies();
    String name = reqCookie[0].getValue();
    String pwd = reqCookie[1].getValue();
    String sql_1 =
        "select * from app1_user where username = 'hujj'" + " AND pwd = '" + pwd + "';";
    String sql_2 =
        "select * from app1_user where username = '"+name+"'" + " AND pwd = 'hujj';";
    ExecuteSql.addBatchAndCleanBatch(sql_1);
    ExecuteSql.addBatchAndCleanBatch(sql_2);
  }

  @RequestMapping("/injection2.do")
  public void injection2(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Cookie[] reqCookie = request.getCookies();
    String name = reqCookie[0].getValue();
    String pwd = reqCookie[1].getValue();
    String sql_1 =
        "select * from app1_user where username = '"+name+"'" + " AND pwd = '" + pwd + "';";
    String sql_2 =
        "select * from app1_user where username = '"+name+"'" + " AND pwd = '" + pwd + "';";
    ExecuteSql.addBatchAndCleanBatch(sql_1);
    ExecuteSql.addBatchAndCleanBatch(sql_2);
  }


}
