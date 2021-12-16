package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import play.mvc.Http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping("trustboundary")
public class TrustBoundartController {

  @RequestMapping("/tbv001.do")
  public void tbv1(HttpServletRequest request, HttpServletResponse response) {
    String sessionPolicyId = request.getParameter("id");
    HttpSession session = request.getSession();
    session.setAttribute("sessionPolicyId", sessionPolicyId);
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      out.write("上报漏洞：非可信数据混入可信区域");
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
