package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weakPasswordPolicy")
public class WeakPasswordPolicyController {

  /**
   * 测试weak-password-policy的逻辑是：
   * 首先收集应用程序中form表单中type=password的input组件的id和name;
   * 然后再检测queryString中的参数的key是否为密码；
   * 如果是key为密码，则检查密码的value强弱
   * @param request
   * @param response
   * @throws IOException
   * @throws ServletException
   */
  @RequestMapping("/testWeakPwd.do")
  public void testWeakPwd(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String password = "admin";
    String pwd = request.getParameter("pwd");
    PrintWriter out = response.getWriter();
    if (password.equals(pwd)) {
      out.println("<font color=red>Welcome</font>");
    } else {
      out.println("<font color=red>Either user name or password is wrong.</font>");
    }
  }

  @RequestMapping("/weakPwdPolicy001.do")
  public void testWeakPwd1(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String password = "admin";
    //weakpwdpolicy/weakPwd.jsp中有定义id和name等于pwd1的密码框，所以该请求会上报weak-password-policy
    String pwd = request.getParameter("pwd1");
    PrintWriter out = response.getWriter();
    if (password.equals(pwd)) {
      out.println("<font color=red>Welcome</font>");
    } else {
      out.println("<font color=red>Either user name or password is wrong.</font>");
    }
  }

  @RequestMapping("/test.do")
  public void test(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String userID = "admin";
    String user = request.getParameter("password");
    PrintWriter out = response.getWriter();
    if (userID.equals(user)) {
      out.println("<font color=red>Welcome</font>");
    } else {
      out.println("<font color=red>Either user name or password is wrong.</font>");
    }
  }

}
