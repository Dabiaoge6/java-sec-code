package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("sessionfixation1")
public class SessionFixationController1 {

  @RequestMapping(value = "/login1.do")
  public void sessionFixation1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;charset=utf-8");
    String jsessionid = getCookie(request,"JSESSIONID");
    Cookie cookie = new Cookie("JSESSIONID", jsessionid);
    cookie.setPath("/");
    response.addCookie(cookie);

    /*PrintWriter out = response.getWriter();
    String sessionid = request.getParameter("sessionid");
    Cookie cookie = new Cookie("sessionid", sessionid);
    cookie.setPath("/");
    response.addCookie(cookie);
    //response.setHeader("set-cookie", "JSESSIONID=" + jsessionid);
    //out.println("cookie 设置为"+sessionid);
    if(sessionid.equals("admin")){
      out.println("fixsession设置为admin,为管理员权限");
    }else {
      out.println("fixsession设置为admin,为user权限");
    }*/
    /*HttpSession session = request.getSession(false);
    if (session != null) {
      out.println("login success,old SESSIONID=" + session.getId());
      //response.setHeader("set-cookie", "JSESSIONID=" + session.getId());
      //session.invalidate();
    }
    //out.println("sessionFixation test login success,SESSIONID=" + session != null ? session.getId() : "session is null");
    //out.println("/login1 is report");
    out.close();*/
  }

  private String getCookie(HttpServletRequest request, String id){
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      // 遍历数组
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(id)) {
          // 设置cookie的值
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  @RequestMapping(value = "/login2.do")
  public void sessionFixation2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession(false);
    PrintWriter out = response.getWriter();
    if (session != null) {
      out.println("login success,old SESSIONID=" + session.getId());
      session.invalidate();
    }
    HttpSession newSession = request.getSession(true);
    out.println("new SESSIONID=" + newSession.getId());
    //out.println("/login2 is not report");
    out.close();
  }

}
