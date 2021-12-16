package org.spring.demo.controller.scan;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("scanIssue/csrf")
public class ScanCsrfController {
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        /*if(StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(pwd)){
            Cookie cookie = new Cookie("sessionid","fixed");
            cookie.setPath("/");
            response.addCookie(cookie);
        }*/
        response.sendRedirect("/vul.jsp");
    }


    @RequestMapping("/unsafe.do")
    public void csrfUnsafeRequest(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            String name = request.getParameter("name");
            Integer age = Integer.valueOf(request.getParameter("age"));
            out = response.getWriter();
            //String token = request.getHeader("token");
            /*if(StringUtils.isEmpty(token)){
                out.println("请求header中不包含token属性，请求存在csrf风险");
            }else {
                out.println("请求header中包含token属性，请求安全");
            }*/
            out.println("unsafe query success,name="+name+",age="+age);
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }
}
