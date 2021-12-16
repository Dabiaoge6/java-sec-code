package org.spring.demo.controller.vulnercontroller.isit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 *敏感信息通过请求传输
 */

@Controller
@RequestMapping("isit")
public class IsitReqestController {

	@RequestMapping("/isit_post.do")
	public void isit1Post(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String p1 = request.getQueryString();
		String idCard = request.getParameter("idCard");
		out.println("上报漏洞：敏感信息通过请求传输");
		out.close();
	}
}