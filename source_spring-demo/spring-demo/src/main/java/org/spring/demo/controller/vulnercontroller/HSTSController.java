package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hsts")
public class HSTSController {
	@RequestMapping("/hsts.do")
	public void hstsNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Strict-Transport-Security", "max-age=10000");
		PrintWriter out = response.getWriter();
		out.println("If the request is requested by HTTPS,the request don't report hsts");
	}


	@RequestMapping("/maxage1.do")
	public void httpsMaxage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Strict-Transport-Security", "max-age=0");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：缺少hsts响应头");
	}

}
