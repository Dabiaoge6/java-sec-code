package org.spring.demo.controller.vulnerFix.untrustedDeserial;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vulhunter.common.untrustdeserialization.UntrustdeserialCommon;
import org.vulhunter.common.untrustdeserialization.User;
import org.vulhunter.common.untrustdeserialization.XStreamUtil;
import org.vulhunter.vulnerfix.untrustdeserial.UntrustDeserialFixCommon;
import org.vulhunter.vulnerfix.untrustdeserial.UserObjectInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Controller
@RequestMapping("untrustedDeserialFix")
public class UntrustedDeserialFixController {

  private static Object deserializeSelf(byte[] buffer) throws IOException, ClassNotFoundException {
    ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
    ObjectInputStream ois = new UserObjectInputStream(bis);
    Object obj = ois.readObject();
    ois.close();
    bis.close();
    return obj;
  }

  @RequestMapping(value = "/objectDeserialFix.do")
  public void parseObject(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ClassNotFoundException {
    String name = request.getParameter("name");
    String mobile = request.getParameter("mobile");
    User user = new User();
    user.setName(name);
    user.setMobile(mobile);
    byte[] buffer = UntrustdeserialCommon.serialize(user);
    Object user1 = deserializeSelf(buffer);
  }

  @RequestMapping(value = "/XStream/fromXML_StrFix.do")
  public void fromXML_Str(@RequestParam String xmlString, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    UntrustDeserialFixCommon.setSecurity();
    XStream xstream = new XStream();
    xstream.alias("user", User.class);
    User user = (User) xstream.fromXML(xmlString);
  }

  @RequestMapping(value = "/XStream/fromXML.do")
  public void fromXML(@RequestParam String xmlString, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    User user = (User) XStreamUtil.fromXML(xmlString, User.class);
    response.getWriter().println(user.toString());
    Throwable stackHolder = new Throwable();
    StackTraceElement[] stackTrace = stackHolder.getStackTrace();
    for(StackTraceElement stackTraceElement:stackTrace){
      System.out.println(stackTraceElement.getClassName());
    }
  }

  @RequestMapping(value = "/XStream/fromXML1.do")
  public void fromXML1(@RequestParam String xmlString, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    XStream xStream = new XStream(new StaxDriver());
    //解决安全警告: Security framework of XStream not initialized, XStream is probably vulnerable.
    XStream.setupDefaultSecurity(xStream);
    xStream.ignoreUnknownElements();
    //该package下的类允许解析
    xStream.allowTypesByWildcard(new String[]{"org.vulhunter.common.untrustdeserialization.User"});
    xStream.processAnnotations(User.class);
    User user = (User) xStream.fromXML(xmlString);
    response.getWriter().println(user.toString());
    Throwable stackHolder = new Throwable();
    StackTraceElement[] stackTrace = stackHolder.getStackTrace();
    for(StackTraceElement stackTraceElement:stackTrace){
      System.out.println(stackTraceElement.getClassName());
    }
  }

}
