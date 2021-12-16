package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("cookieSecure")
public class CookieSecureController {

	@RequestMapping("/secure_false.do")
	public void FalseSecure(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Set-Cookie", "secure=false ;HttpOnly");
		Cookie cookie = new Cookie("name", "httpOnlyfalse");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：1.cookie缺少secure属性 2.缺少hsts响应头");
	}
}
