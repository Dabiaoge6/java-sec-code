package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("redos")
public class RedosController {

  @RequestMapping("/redos001.do")
  public void testEvilPattern(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String pattern = request.getParameter("pattern");
    String regex = request.getParameter("regex");
    Pattern p = Pattern.compile(pattern);
    Matcher match = p.matcher(regex);
    if (match.matches()) {
      out.println("Do not include name in password.");
    } else {
      out.println("上报漏洞：正则表达式攻击");
    }
  }

  @RequestMapping("/redos002.do")
  public void redos002(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String requestUri = request.getRequestURI();
    String path = request.getContextPath();
    Pattern testPassword = Pattern.compile(requestUri);
    Matcher match = testPassword.matcher(path);
    if (match.matches()) {
      out.println("matches success");
    } else {
      out.println("matches fail");
    }
  }

  @RequestMapping("/testPattern.do")
  public void testPattern(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    PrintWriter out = response.getWriter();
    String regex = "([a-zA-Z]+)*";
    String pwd = request.getParameter("pwd");
    Pattern testPassword = Pattern.compile(regex);
    Matcher match = testPassword.matcher(pwd);
    if (match.matches()) {
      out.println("the regex is evil.");
    } else {
      out.println("Good password.");
    }
  }

}
