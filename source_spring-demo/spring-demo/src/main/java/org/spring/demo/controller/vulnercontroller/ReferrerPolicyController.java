package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("referrerPolicy")
public class ReferrerPolicyController {

	@RequestMapping("/refer001.do")
	public void testDisable(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Referrer-Policy","no-referrer-when-downgrade");
		PrintWriter out = response.getWriter();
		out.println("the request don't report Referrer-Policy-Missing");
	}

	@RequestMapping("/refer002.do")
	public void testAble(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("the request report Referrer-Policy-Missing");
	}

}
