package org.spring.demo.controller.detectRuleConfig.customVulRule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

@Controller
@RequestMapping("xpathAuto")
public class XpathAuto {


	private void end(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：Xpath注入");
		out.close();
	}

	@RequestMapping("/xpath.do")
	public void xpathInjection2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String xpathUrl = request.getParameter("xpath");
		String xml = "<resp><status>good</status><msg>hi</msg></resp>";
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		InputSource source = new InputSource(new StringReader(xml));
		String status = "";

		try {
			status = (String) xpath.evaluate(xpathUrl, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		end(response);
	}
}


