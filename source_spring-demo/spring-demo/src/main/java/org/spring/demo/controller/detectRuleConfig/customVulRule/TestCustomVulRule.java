package org.spring.demo.controller.detectRuleConfig.customVulRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.sqlinjection.SqlInjectionCommon;
import org.vulhunter.customrule.CustomRule;

@Controller
@RequestMapping("customVulRule")
public class TestCustomVulRule {

  private void end(HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("CmdInjection");
    out.close();
  }

  @RequestMapping("/inputValidator.do")
  public void cmdInjection1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String serverId = System.getProperty("server.detector.server.id");
    String cmdStr = request.getParameter("cmd");
    //自定义规则-input validate
    if (!CustomRule.doInputValidator(cmdStr)) {
      return;
    }
    Throwable throwable = new Throwable();
    StackTraceElement[] stackTraceElements = throwable.getStackTrace();
    for(StackTraceElement stackTraceElement:stackTraceElements){
      String className = stackTraceElement.getClassName();
      if("CustomRule".contains(className)){
        System.out.println("true");
      }
    }
    System.out.println("============tempStr:=========" + cmdStr + "================");
    Process pro = Runtime.getRuntime().exec(cmdStr);
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
    String msg = null;
    while ((msg = bufReader.readLine()) != null) {
      System.out.println(msg + "<----msg");
    }
    end(response);
  }

  // http://localhost:8086/spring-demo/cmdInjection/cmdInjection5.do?cmd=mspaint
  @RequestMapping("/sanitizer.do")
  public void cmdInjection5(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String cmdStr = request.getParameter("cmd");
    CustomRule customRule = new CustomRule();
    //自定义规则-Sanitizer
    String cmdStr2 = customRule.doSanitizer(cmdStr);
    Throwable throwable = new Throwable();
    StackTraceElement[] stackTraceElements = throwable.getStackTrace();

    for(StackTraceElement stackTraceElement:stackTraceElements){
      String className = stackTraceElement.getClassName();
      if("CustomRule".contains(className)){
        System.out.println("true");
      }
    }
    Process pro = Runtime.getRuntime().exec(cmdStr2);
    BufferedReader bufReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
    String msg = null;
    while ((msg = bufReader.readLine()) != null) {
      System.out.println(msg + "<----msg");
    }
    end(response);
  }

  @RequestMapping("/matcher.do")
  public void sqlInjection(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
    CustomRule1 customRule1 = new CustomRule1();
    customRule1.regMatcher(request);
    /*SqlInjectionFixCommon sqlInjectionTestService = new SqlInjectionFixCommon();
    sqlInjectionTestService.preparedstatementAddBatch(request);*/
      PrintWriter out = response.getWriter();
      out.println("正则过滤器");
    }
}
