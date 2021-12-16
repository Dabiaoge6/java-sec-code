package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.spring.demo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reflectionInjection")
public class ReflectionInjectionController {

  @RequestMapping("/reflect001.do")
  public void reflect001(HttpServletRequest request, HttpServletResponse response)
      throws IOException, NoSuchFieldException {
    String declaredField = request.getParameter("declaredField");
    User user = new User();
    Field declaredField1 = user.getClass().getDeclaredField(declaredField);
    PrintWriter out = response.getWriter();
    out.println("reflection-injection");
  }

  @RequestMapping("/reflect002.do")
  public void reflect002(HttpServletRequest request, HttpServletResponse response)
      throws IOException, NoSuchFieldException {
    String field = request.getParameter("field");
    User user = new User();
    Field field1 = user.getClass().getDeclaredField(field);
    PrintWriter out = response.getWriter();
    out.println("reflection-injection");
  }

  @RequestMapping("/reflect003.do")
  public void reflect003(HttpServletRequest request, HttpServletResponse response)
      throws IOException, NoSuchMethodException {
    String declaredMethod = request.getParameter("declaredMethod");
    User user = new User();
    Method declaredMethod1 = user.getClass().getDeclaredMethod(declaredMethod);
    PrintWriter out = response.getWriter();
    out.println("reflection-injection");
  }

  @RequestMapping("/reflect004.do")
  public void reflect004(HttpServletRequest request, HttpServletResponse response)
      throws IOException, NoSuchMethodException {
    String method = request.getParameter("method");
    User user = new User();
    Method method1 = user.getClass().getMethod(method,String.class);
    PrintWriter out = response.getWriter();
    out.println("reflection-injection");
  }

}
