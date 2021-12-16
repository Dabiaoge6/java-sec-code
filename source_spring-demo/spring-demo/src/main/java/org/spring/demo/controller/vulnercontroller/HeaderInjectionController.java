package org.spring.demo.controller.vulnercontroller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;


@Controller
@RequestMapping("headerInjection")
public class HeaderInjectionController {
	
	private void end(HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println("HeaderInjection");
		out.close();
	}
	
    @RequestMapping("/headerInjection001.do")
    public void headerInjection1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //----> http://localhost:8086/spring-demo/headerInjection/headerInjection1.do?testurl=test
        String url = request.getParameter("testurl");
        url = URLEncoder.encode(url, "utf-8");
        url = url.replace("\r","").replace("\n","");
        String url1 = url.toLowerCase(Locale.getDefault());
        response.setHeader("test", url1);
        end(response);
    }

  @RequestMapping("/headerInjectionUpper.do")
  public void headerInjectionUpper(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //----> http://localhost:8086/spring-demo/headerInjection/headerInjectionUpper.do?testurl=test
    String url = request.getParameter("testurl");
    url = URLEncoder.encode(url, "utf-8");
    url = url.replace("\r","").replace("\n","");
    String url1 = url.toUpperCase(Locale.getDefault());
    response.setHeader("Content-Disposition", "attachment; filename=" + url1);
    end(response);
  }

    @RequestMapping("/headerInjection002.do")
    public void headerInjection2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("testurl");
        response.addHeader("test", url + "\r\n");
      response.addHeader("lht", url+"\r\nwe:sd");
        end(response);
    }

  @RequestMapping("/headerInjection.do")
  public void headerInjection(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String url = request.getParameter("testurl");
    if(url.contains("\r\n")){
      String[] h1 = url.split("\r\n");
      response.addHeader("test", h1[0]);
      String[] h2 = h1[1].split(":");
      response.setHeader(h2[0],h2[1]);
    }else{
      response.addHeader("test", url);
    }
      PrintWriter out = response.getWriter();
      out.println("上报漏洞：Http响应头截断");
      out.close();
  }

}
