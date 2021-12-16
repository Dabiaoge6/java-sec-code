package org.spring.demo.controller.vulnercontroller;

import org.spring.demo.crawler.helper.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @BelongsProject: vulhunter-demo
 * @BelongsPackage: org.spring.demo.controller.vulnercontroller
 * @Author: WangWei
 * @CreateTime: 2020-06-08 13:39
 * @Description: 会话锁定漏洞样例
 */
@Controller
@RequestMapping(value = "sessionFixed")
public class SessionFixedController {
    @RequestMapping(value = "/login1.do")
    public void sessionFixedLogin1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        if (session != null) {
            out.println("SessionFixed login request success," + "Old SESSIONID=" + session.getId());
            response.setHeader("set-cookie", "JSESSIONID=" + session.getId());
            session.invalidate();
            out.println("sessionFixation test login success,SESSIONID="+(session != null ? session.getId() : "session is null"));
            session.setAttribute("user1", "user1");
            out.close();
        }
    }
    @RequestMapping(value = "/login2.do")
    public void sessionFixedLogin2(HttpServletRequest request, HttpServletResponse response) throws IOException {
            HttpSession session = request.getSession(false);
            PrintWriter out = response.getWriter();
            if (session != null) {
                out.println("SessionFixed login request success," + "Old SESSIONID=" + session.getId());
                response.setHeader("set-cookie", "JSESSIONID=" + session.getId());
                session.invalidate();
                out.println("sessionFixation test login success,SESSIONID=" + (session != null ? session.getId() : "session is null"));
                HttpSession newSession = request.getSession(true);
                out.println("sessionFixation test login success,new SESSIONID=" + newSession.getId());
                session.setAttribute("user2", "user2");
                out.close();
            }
        }
    @RequestMapping(value = "/logout1.do")
    public void sessionFixedLogout1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    out.println("JSESSIONID="+cookie.getValue());
                }
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        out.println("User="+session.getAttribute("user1"));
        if(session != null){
            session.invalidate();
        }
        out.println("User1 already logged out");
    }
    @RequestMapping(value = "/logout2.do")
    public void sessionFixedLogout2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    out.println("JSESSIONID="+cookie.getValue());
                }
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        out.println("User="+session.getAttribute("user2"));
        if(session != null){
            session.invalidate();
        }
        out.println("User2 already logged out");
    }

}
