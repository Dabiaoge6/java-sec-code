package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cookiehttp")
public class CookieHttpOnlyController {

	@RequestMapping("/cookieflag.do")
	public void cookieflag(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie cookie = new Cookie("name", "httpOnlyfalse");
		cookie.setPath("/spring-demo/cookiehttp/cookieflag.do");
		response.addCookie(cookie);
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：cookie缺少httponly属性");
	}
}
