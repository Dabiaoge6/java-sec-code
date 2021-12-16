package org.spring.demo.controller.detectRuleConfig.testsubstring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

@Controller
@RequestMapping("testSubString")
public class TestSubStringController {

  @RequestMapping("/xss001.do")
  public void xss001(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    String subStringName = inputName.substring(4);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(subStringName, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss002.do")
  public void xss002(HttpServletRequest request, HttpServletResponse response) {
    String inputName = request.getParameter("username");
    String subStringName = inputName.substring(3,9);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
    try {
      StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
      stringHttpMessageConverterExtends
          .getWriteInternal(subStringName, outputMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/xss003.do")
  public void xss003(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String testString = request.getParameter("username");//test-username
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();
    String[] arr = {"a", "b", "c", "d"};

    StringBuilder stringBuilder = new StringBuilder(testString);//test-username
    String str = stringBuilder.substring(1);//st-username
    writer.format(Locale.getDefault(), str, 100, arr);
    writer.flush();
    writer.close();
  }

  @RequestMapping("/xss004.do")
  public void xss004(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String testString = request.getParameter("username");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();

    StringBuilder stringBuilder = new StringBuilder(testString);
    String str = stringBuilder.substring(2,12);
    writer.format(Locale.getDefault(), str);
    writer.flush();
    writer.close();
  }

  @RequestMapping("/xss005.do")
  public void xss005(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String testString = request.getParameter("testString");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();
    StringBuffer stringBuffer = new StringBuffer(testString);
    String str = stringBuffer.substring(4);
    writer.print(str);// String

    writer.flush();
    writer.close();
  }

  @RequestMapping("/xss006.do")
  public void xss006(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String testString = request.getParameter("testString");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter writer = response.getWriter();
    StringBuffer stringBuffer = new StringBuffer(testString);
    String str = stringBuffer.substring(0,8);
    writer.print(str);// String

    writer.flush();
    writer.close();
  }

}
