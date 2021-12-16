package org.spring.demo.controller.vulnercontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import play.mvc.WebSocket;

import java.io.IOException;

@Controller
@RequestMapping("sessionTimeOut")
public class SessionTimeOutController {

  @RequestMapping("/session001.do")
  public void session001(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    int time = Integer.parseInt(request.getParameter("time"));
    session.setMaxInactiveInterval(time);
    response.getWriter().println("上报漏洞：不正确的会话时间配置");
  }
}
