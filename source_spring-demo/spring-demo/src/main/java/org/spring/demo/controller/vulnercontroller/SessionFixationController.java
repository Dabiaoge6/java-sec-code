package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("sessionfixation")
public class SessionFixationController {

  @RequestMapping(value = "/login1.do")
  public void sessionFixation1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);
    if (session != null) {
     // out.println("sessionFixation test login success,old SESSIONID=" + session.getId());
      response.setHeader("Set-Cookie", "secure=false ;HttpOnly");
      response.setHeader("set-cookie", "JSESSIONID=" + session.getId());
      session.invalidate();
    }
   // out.println("sessionFixation test login success,SESSIONID=" + session != null ? session.getId() : "session is null");
    out.println("上报漏洞：会话固定");
    out.close();
  }
}
