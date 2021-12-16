package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("contentPolicy")
public class ContentPolicyController {

	@RequestMapping("/missCsp003.do")
	public void cspResponseMissingTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.getWriter().print("no Content-Security-Policy header");
	}

	@RequestMapping("/csp004.do")
	public void cspResponseTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Security-Policy", "script-src 'self';" + "object-src 'none';"
				+ "child-src https:");
		response.getWriter().print("set Content-Security-Policy");
	}
}
