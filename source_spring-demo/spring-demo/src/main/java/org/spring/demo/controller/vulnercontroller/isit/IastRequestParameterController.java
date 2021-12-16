package org.spring.demo.controller.vulnercontroller.isit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.vulnercontroller.IsitController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 敏感信息通过RequestParameter传输
 */

@Controller
@RequestMapping("/isit")
public class IastRequestParameterController {

	private static final Logger logger = LoggerFactory.getLogger(IastRequestParameterController.class);

	@RequestMapping("/isit6.do")
	public void isit6(@RequestParam("xx") String id, HttpServletRequest request,
					  HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：敏感信息通过RequestParameter传输");
		out.close();
	}

	@RequestMapping("/isit7.do")
	public void isit7(@RequestParam("xx") String id, HttpServletRequest request,
					  HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		logger.info(id);
//		out.println("1.敏感信息通过log传输 2.敏感信息通过RequestParameter传输3.日志注入");
		out.println("1.敏感信息通过log传输 2.日志注入 3.敏感信息通过请求传输");
		out.close();
	}
}
