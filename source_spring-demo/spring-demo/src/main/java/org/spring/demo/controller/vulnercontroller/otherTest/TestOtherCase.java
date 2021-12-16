package org.spring.demo.controller.vulnercontroller.otherTest;

import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.service.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hujiaojiao
 * @date 2019/4/10
 */
@Controller
public class TestOtherCase {

  @Autowired
  private UserService userService;

  @RequestMapping("/exceptionXss.do")
  @ResponseBody
  public Object testExceptionXss(@RequestParam(value = "start") String start,
      HttpServletRequest req,
      HttpServletResponse res) throws Exception {
    System.out.println(start);
    int a = Integer.valueOf(start);
    return "test";
  }

  /*@RequestMapping("/requestBodyRepeat.do")
  public void requestBodyRepeat(MyRequest req,
      HttpServletResponse res) throws Exception {
    PrintWriter out = res.getWriter();
    ServletInputStream inputStream = req.getInputStream();
    String requestBody = "";
    byte[] cache = new byte[1024];
    int len = 0;
    while ((len = inputStream.read(cache)) > 0) {
      requestBody = new String(cache, 0, len);
    }
    out.print(requestBody);
    out.close();

  }*/

  @RequestMapping("/testSqlConcat.do")
  public void testSqlConcat(HttpServletRequest req,
      HttpServletResponse res) {
    String name = req.getParameter("username");
    List<String> idList = userService.fuzzyQueryId(name);
    if (idList.size() != 0) {
      System.out.println("the list is not empty");
    } else {
      System.out.println("the list is empty");
    }
  }

  @RequestMapping("/testSqlConcatPre.do")
  public void testSqlConcat1(HttpServletRequest req,
      HttpServletResponse res) {
    String name = req.getParameter("username");
    List<String> idList = userService.fuzzyQueryIdPre(name);
    if (idList.size() != 0) {
      System.out.println("the list is not empty");
    } else {
      System.out.println("the list is empty");
    }
  }

}
