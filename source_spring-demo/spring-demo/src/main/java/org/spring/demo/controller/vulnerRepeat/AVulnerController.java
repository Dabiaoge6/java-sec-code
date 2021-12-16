package org.spring.demo.controller.vulnerRepeat;

import org.spring.demo.controller.vulnerRepeat.common.VulnerCommon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.sqlinjection.ExecuteSql;
import org.vulhunter.common.sqlinjection.SqlInjectionCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("aVulner")
public class AVulnerController {

  //不同url,同一个ruleId,相同参数，相同source和sink函数，不同用户代码
  @RequestMapping("/aRepeat001.do")
  public void aRepeat001(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementAddBatch2(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  @RequestMapping("/aRepeat002.do")
  public void aRepeat002(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.statementAddBatch2(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  @RequestMapping("/aRepeat003.do")
  public void aRepeat003(HttpServletRequest request, HttpServletResponse responese)
      throws IOException {
    SqlInjectionCommon sqlInjectionTestService = new SqlInjectionCommon();
    sqlInjectionTestService.testStatementAddBatch(request);
    PrintWriter out = responese.getWriter();
    out.println("test Statement.addBatch");
  }

  //同一个url,同一个参数，同一个source函数，不同sink函数
  @RequestMapping("/aRepeat004.do")
  public void aRepeat004(HttpServletRequest request, HttpServletResponse responese)
      throws IOException, InterruptedException {
    String username = request.getParameter("username");
    String sql = "SELECT u.id FROM user_t u WHERE u.user_name = '" + username + "';";
    System.out.println(
        "-----------------------------------query sql----------------------------------------");
    System.out.println(sql);
    ExecuteSql.addBatchAndCleanBatch(sql);
    ExecuteSql.execute(sql);
  }

  //不安全哈希算法
  @RequestMapping("/aRepeat005.do")
  public void aRepeat005(HttpServletRequest request, HttpServletResponse responese)
      throws NoSuchAlgorithmException {
    VulnerCommon vulnerCommon = new VulnerCommon();
    vulnerCommon.md5();
  }

  @RequestMapping("/aRepeat006.do")
  public void aRepeat006(HttpServletRequest request, HttpServletResponse responese)
      throws NoSuchAlgorithmException {
    VulnerCommon vulnerCommon = new VulnerCommon();
    vulnerCommon.testMD5(request);
  }

}
