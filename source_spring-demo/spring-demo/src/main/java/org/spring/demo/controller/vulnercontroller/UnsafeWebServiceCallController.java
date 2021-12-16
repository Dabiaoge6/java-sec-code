package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("unsafeWebCall")
public class UnsafeWebServiceCallController {

  @RequestMapping("/webCall001.do")
  public void webCall001(HttpServletRequest request,HttpServletResponse response){
    String urlInput = "http://www.baidu.com";
    try {
      URL url1 = new URL(urlInput);
      url1.openConnection();
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.println("上报漏洞：web服务调用不安全");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
