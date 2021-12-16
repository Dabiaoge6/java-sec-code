package org.spring.demo.controller;

import org.service.demo.service.UserService;
import org.spring.demo.custom.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.UUID;

@Controller
public class TestController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/test/{uuid}")
  public void test(@PathVariable String uuid, HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    System.out.println(uuid);
  }

  @RequestMapping(value = "/test1.do")
  public void testLoaded(HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    out.println("this is a test case by hujj");
  }


  @RequestMapping(value = "/registerTest.do")
  public void registerFormData(HttpServletRequest req,
      HttpServletResponse res) throws IOException {
    String requestBody = "";
    byte[] cache = new byte[1024];
    PrintWriter out = res.getWriter();
    ServletRequest requestWrapper = new RequestWrapper(req);
    InputStream inputStream = requestWrapper.getInputStream();
    int len = 0;
    while ((len = inputStream.read(cache)) > 0) {
      requestBody = new String(cache, 0, len);
    }

    if (requestBody == null || "".equals(requestBody)) {
      return;
    }
    String[] split = requestBody.split("&");
    String name = split[1];
    String pwd = split[2];
    int age = 25;
    String uuid = UUID.randomUUID().toString();
    String id = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18)
        + uuid.substring(19, 23) + uuid.substring(24);
    //sql-injection会影响xss的上报，先注释
    boolean result = userService.insert(id, name, pwd, age);
//    boolean result = true;
    if (!result) {
      out.print("sorry," + name + " registere fail");
    }
    out.print(requestBody + " registere success");
    out.close();
  }

}
