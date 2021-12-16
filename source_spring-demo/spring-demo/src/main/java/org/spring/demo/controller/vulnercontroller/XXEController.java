package org.spring.demo.controller.vulnercontroller;

import nu.xom.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vulhunter.common.xxe.*;
import org.vulhunter.common.xxe.saxHandler.SAXContentHandler;
import org.vulhunter.common.xxe.saxHandler.SAXHandler;
import org.vulhunter.common.xxe.saxHandler.SAXHandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("xxe")
public class XXEController {

  @RequestMapping(value = "/xxe016.do")
  public void xmlReader(@RequestParam String xmlString, HttpServletRequest request,
      HttpServletResponse response) {
    PrintWriter out = null;
    // p2o
    StringReader sr = new StringReader(xmlString);
    // p2o
    InputSource is = new InputSource(sr);
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser sp = null;
    try {
      out = response.getWriter();
      sp = spf.newSAXParser();
      XMLReader xmlReader = sp.getXMLReader();
      SAXContentHandler handler = new SAXContentHandler();
      xmlReader.setContentHandler(handler);
      xmlReader.parse(is);
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
}
