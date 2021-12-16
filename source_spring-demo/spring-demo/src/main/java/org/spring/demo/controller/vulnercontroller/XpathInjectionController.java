package org.spring.demo.controller.vulnercontroller;

import org.apache.xml.utils.PrefixResolverDefault;
import org.apache.xpath.XPathAPI;
import org.apache.xpath.objects.XObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.*;
import java.io.*;

@Controller
@RequestMapping("xpathInjection")
public class XpathInjectionController {
	
	private void end(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：Xpath注入");
		out.close();
	}

    @RequestMapping("/xpath002.do")
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
