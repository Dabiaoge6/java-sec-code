package org.spring.demo.controller.vulnerFix.xpath;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("xpathFix")
public class XpathFixController {

    private void end(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("XpathInjection");
        out.close();
    }

    // http://localhost:8086/spring-demo/xpathInjection/xpathInjection4.do?xpath=//book[author='Neal Stephenson']/title/text()
    @RequestMapping("/xpathFix.do")
    public void xpathInjection2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        Document doc = null;
        String path = request.getSession().getServletContext().getRealPath("") + "\\static\\xml";
        String filename = path + "\\" + "test.xml";
        File testFile = new File(filename);

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(testFile);
            //打印根节点下的所有元素节点
            System.out.println(doc.getDocumentElement().getChildNodes().getLength());
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(nodeList.item(i).getNodeName() + " ");
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathParameterizingResolver resolver = new XPathParameterizingResolver();
        resolver.addVariable(new QName(null, "first"), first);
        resolver.addVariable(new QName(null, "last"), last);
        xpath.setXPathVariableResolver(resolver);

        //4.1 ====> 正常测试
        try {
            XPathExpression xPathExpression = xpath.compile("/people/person[@first=$first and @last=$last]");
            NodeList nodeList =  (NodeList)xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.print(nodeList.item(i).getNodeName() + "-->"
                        + nodeList.item(i).getTextContent() + " ");
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        end(response);
    }

}
