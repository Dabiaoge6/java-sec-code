package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("unsafeLogout")
public class UnsafeLogoutController {

  @RequestMapping("/login.do")
  public void login(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String userID = "admin";
    String password = "password";
    String user = request.getParameter("user1");
    String pwd = request.getParameter("pwd1");

    if (userID.equals(user) && password.equals(pwd)) {
      HttpSession session = request.getSession();
      session.setAttribute("user", "Hujj");
      //setting session to expiry in 30 mins
      session.setMaxInactiveInterval(30 * 60);
      Cookie userName = new Cookie("user", user);
      response.addCookie(userName);
      response.setHeader("Set-Cookie", "secure=false ;HttpOnly");
      String encodedURL = response.encodeRedirectURL("../unsafeLogout/loginout.jsp");
      response.sendRedirect(encodedURL);
    } else {
      RequestDispatcher rd = request.getRequestDispatcher("../unsafeLogout/login.jsp");
      PrintWriter out = response.getWriter();
      out.println("<font color=red>Either user name or password is wrong.</font>");
      rd.include(request, response);
    }
  }

  @RequestMapping("/logout.do")
  public void loginout(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("JSESSIONID")) {
          System.out.println("JSESSIONID=" + cookie.getValue());
        }
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setHeader("Set-Cookie", "secure=false ;HttpOnly");
      }
    }
    //invalidate the session if exists
    HttpSession session = request.getSession(false);
    System.out.println("User=" + session.getAttribute("user"));
    //注释掉该invalidate(),会导致session无法解绑在session中的对象，有unsafe-logout
//    if (session != null) {
//      session.invalidate();
//    }
    //no encoding because we have invalidated the session
//    response.sendRedirect("../unsafeLogout/login.jsp");
    response.getWriter().println("上报漏洞：不安全的退出");
  }
}
