package org.spring.demo.controller.vulnercontroller.isit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.vulnercontroller.IsitController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 敏感信息通过URL传输
 */
@Controller
@RequestMapping("isit")
public class IastUrlController {
	@RequestMapping("/{id}/testIdNum.do")
	public void testId(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
		  throws  IOException {
	PrintWriter out = response.getWriter();
    out.println("上报漏洞：敏感信息通过url传输");
	out.close();
	}
}
