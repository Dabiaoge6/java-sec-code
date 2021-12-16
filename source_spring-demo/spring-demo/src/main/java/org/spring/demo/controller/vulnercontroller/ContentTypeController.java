package org.spring.demo.controller.vulnercontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contentType")
public class ContentTypeController {

	@RequestMapping("/contentTypeResponseMissing.do")
	public void cspResponseMissingTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.getWriter().print("no content-type header");
	}

	@RequestMapping("/contentTypeResponse.do")
	public void cspResponseTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print("set content-type");
	}
}
