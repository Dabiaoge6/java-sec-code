package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("xssProtection")
public class XSSProtectionController {

	@RequestMapping("/testDisable.do")
	public void testDisable(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-XSS-Protection", "0");
		PrintWriter out = response.getWriter();
		out.println("the request report X-XSS-Protection-disable");
	}

	@RequestMapping("/testAble.do")
	public void testAble(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-XSS-Protection", "1; mode=block");
		PrintWriter out = response.getWriter();
		out.println("the request don't report X-XSS-Protection-disable");
	}

}
