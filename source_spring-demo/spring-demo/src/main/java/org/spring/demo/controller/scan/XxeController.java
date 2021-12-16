package org.spring.demo.controller.scan;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.spring.demo.controller.testjson.bean.XxeSch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vulhunter.common.xxe.Xxe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;

@Controller
@RequestMapping("scanIssue")
public class XxeController {

    @RequestMapping("/query/xxe.do")
    public void queryXml(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JAXBContext context = JAXBContext.newInstance(Xxe.class);
            Unmarshaller um = context.createUnmarshaller();
            String queryString = request.getQueryString();
            String xml = StringUtils.substringAfter(queryString, "xml=");
            Xxe bean = (Xxe) um.unmarshal(IOUtils.toInputStream(URLDecoder.decode(StringEscapeUtils.unescapeXml(xml))));
            out.println(bean.toString());
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }

    @RequestMapping("/stream/xxe.do")
    public void streamXml(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JAXBContext context = JAXBContext.newInstance(Xxe.class);
            Unmarshaller um = context.createUnmarshaller();
            String xml = IOUtils.toString(request.getInputStream());
            Xxe bean = (Xxe) um.unmarshal(IOUtils.toInputStream(URLDecoder.decode(StringEscapeUtils.unescapeXml(xml))));
            out.println(bean.toString());
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/json/xxe.do", method = RequestMethod.POST, consumes = "application/json")
    public void jsonXml(@RequestBody XxeSch sch, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JAXBContext context = JAXBContext.newInstance(Xxe.class);
            Unmarshaller um = context.createUnmarshaller();
            String xml1 = sch.getXml();
            String xml2 = sch.getChief().getTech().getXml();
            Xxe xxe1 = (Xxe) um.unmarshal(IOUtils.toInputStream(URLDecoder.decode(StringEscapeUtils.unescapeXml(xml1))));
            Xxe xxe2 = (Xxe) um.unmarshal(IOUtils.toInputStream(URLDecoder.decode(StringEscapeUtils.unescapeXml(xml2))));
            out.println(xxe1.toString()+"\n"+xxe2.toString());
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }

    @RequestMapping("/form/xxe.do")
    public void kvxml(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            InputStream inputStream = request.getInputStream();
            String s = IOUtils.toString(inputStream, "UTF-8");
            String xml = URLDecoder.decode(StringEscapeUtils.unescapeXml(StringUtils.substringAfter(s,"=")));
            JAXBContext context = JAXBContext.newInstance(Xxe.class);
            Unmarshaller um = context.createUnmarshaller();
            Xxe bean = (Xxe) um.unmarshal(IOUtils.toInputStream(xml));
            out.println(bean.toString());
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/xxeInject.do", method = RequestMethod.POST, consumes = "application/xml")
    public void xxeInject(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            InputStream in = request.getInputStream();
            String inxml = IOUtils.toString(in, "UTF-8");
            System.out.println("inxml="+inxml);
            JAXBContext context = JAXBContext.newInstance(Xxe.class);
            Unmarshaller um = context.createUnmarshaller();
            Xxe bean = (Xxe) um.unmarshal(IOUtils.toInputStream(inxml,"UTF-8"));
            out.println(bean.toString());
        }
        catch (Exception e) {
            out.println("error:" + e.getMessage());
        }
    }
}
