package org.spring.demo.controller.vulnerFix.xxe;

import nu.xom.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vulhunter.common.xxe.Applicant;
import org.vulhunter.common.xxe.Person;
import org.vulhunter.common.xxe.saxHandler.SAXHandler;
import org.w3c.dom.NodeList;
import org.xml.sax.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.*;

@Controller
@RequestMapping("xxeFix")
public class XXEFixController {

  /**
   * SAXParser
   */
  @RequestMapping(value = "/saxParserFix.do")
  public void saxParserFix(HttpServletRequest request, HttpServletResponse response) {
    PrintWriter out = null;
    String xmlName = request.getParameter("xmlName");
    String path = request.getSession().getServletContext().getRealPath("") + "\\static\\xml";
    String filename = path + "\\" + xmlName;
    File testFile = new File(filename);
    // p2o
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser sp = null;
    try {
      out = response.getWriter();
      spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
      spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      InputStream is = new FileInputStream(testFile);
      sp = spf.newSAXParser();
      SAXHandler handler = new SAXHandler();
      sp.parse(is, handler);
      for (Applicant applicant : handler.getApplicantList()) {
        if (applicant != null && !(applicant.equals(""))) {
          out.println(applicant);
        }
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * xom
   */
  @RequestMapping("/xomFix.do")
  public void xomFix(HttpServletRequest request, HttpServletResponse response) {
    String xmlName = request.getParameter("xmlName");
    String path = request.getSession().getServletContext().getRealPath("") + "\\static\\xml";
    String filename = path + "\\" + xmlName;
    PrintWriter out = null;
    try {
      out = response.getWriter();
      File targetFile = new File(filename);
      /*
       * P2O
       */
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      SAXParser saxParser = factory.newSAXParser();
      XMLReader xmlReader = saxParser.getXMLReader(); // now we have a safe xmlReader to use
      Builder parser = new Builder(
          xmlReader); // build a nu.xom.Builder instance that uses the safe reader
      Document document = parser.build(targetFile);
      Elements elements = document.getRootElement().getChildElements();
      for (int i = 0; i < elements.size(); i++) {
        out.println(new Person(elements.get(i)));
      }
    } catch (ValidityException e) {
      e.printStackTrace();
    } catch (ParsingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXNotSupportedException e) {
      e.printStackTrace();
    } catch (SAXNotRecognizedException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  /**
   * Unmarshaller / JAXBContext:
   */
  @RequestMapping(value = "/unmarshallerFix.do")
  public void unmarshallerFix(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    String xmlString = request.getParameter("xmlString");
    PrintWriter out = null;
    try {
      request.setCharacterEncoding("UTF-8");
      out = response.getWriter();
      SAXParserFactory spf = SAXParserFactory.newInstance();
      spf.setFeature("http://xml.org/sax/features/external-general-entities", false);
      spf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      Source xmlSource = new SAXSource(spf.newSAXParser().getXMLReader(),
          new InputSource(new StringReader(xmlString)));
      JAXBContext jc = JAXBContext.newInstance(Applicant.class);
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      Applicant applicant = (Applicant) unmarshaller.unmarshal(xmlSource);
      if (applicant != null && !(applicant.equals(""))) {
        out.println(applicant);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (SAXNotSupportedException e) {
      e.printStackTrace();
    } catch (SAXNotRecognizedException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  /**
   * DocumentBuilder
   */
  @RequestMapping("/documentBuilderFix.do")
  public void documentBuilderFix(HttpServletRequest request, HttpServletResponse response) {
    PrintWriter out = null;
    String xmlName = request.getParameter("xmlName");
    String path = request.getSession().getServletContext().getRealPath("") + "\\static\\xml";
    String filename = path + "\\" + xmlName;
    File testFile = new File(filename);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = null;
    try {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=utf-8");
      dBuilder = dbf.newDocumentBuilder();
      String FEATURE = null;
      FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
      dbf.setFeature(FEATURE, true);
      FEATURE = "http://xml.org/sax/features/external-general-entities";
      dbf.setFeature(FEATURE, false);
      FEATURE = "http://xml.org/sax/features/external-parameter-entities";
      dbf.setFeature(FEATURE, false);
      FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
      dbf.setFeature(FEATURE, false);
      dbf.setXIncludeAware(false);
      dbf.setExpandEntityReferences(false);
      org.w3c.dom.Document doc = dBuilder.parse(testFile);
      NodeList nodeList = doc.getElementsByTagName("applicant");
      org.w3c.dom.Element element = (org.w3c.dom.Element) nodeList.item(0);
      out = response.getWriter();
      out.println(
          "年龄：" + element.getElementsByTagName("age").item(0).getFirstChild().getNodeValue());
      out.println(
          "姓名：" + element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
      out.println(
          "手机号码：" + element.getElementsByTagName("mobile").item(0).getFirstChild().getNodeValue());
      out.println(
          "住址：" + element.getElementsByTagName("address").item(0).getFirstChild().getNodeValue());
      out.println(
          "邮箱：" + element.getElementsByTagName("email").item(0).getFirstChild().getNodeValue());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
