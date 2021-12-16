package org.spring.demo.controller.vulnerFix.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.reflectedxss.ReflectedXssCommon;
import org.vulhunter.vulnerfix.xss.XssFixCommon;

@Controller
@RequestMapping("xssFix")
public class XSSFixController {
	
	@RequestMapping("/writeInternalFix.do")
	public void reflectedXssTestCheck(HttpServletRequest request, HttpServletResponse response) {
		XssFixCommon reflectedXssService = new XssFixCommon();
		reflectedXssService.writeInternalFix(request, response);
	}
	
	@RequestMapping("/jstlFixXss.do")
	public String jstlFixXss(HttpServletRequest request, HttpServletResponse response){
		String message = request.getParameter("message");
		request.setAttribute("message", message);
		return "/reflectedxss/testXss";
	}

}
