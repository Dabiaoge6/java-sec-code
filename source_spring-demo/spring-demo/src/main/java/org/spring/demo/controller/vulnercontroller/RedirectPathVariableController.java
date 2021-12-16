package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping("redirect")
public class RedirectPathVariableController {

  @RequestMapping("/{location}/pathVariable001.do")
  public void pathVariable001(@PathVariable String location, HttpServletRequest request, HttpServletResponse response) {
    try {
      response.sendRedirect("../" + location);
      PrintWriter out = response.getWriter();
      out.println("UnValidated Redirect");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/{name}/pathVariable002.do")
  public void pathVariable002(@PathVariable("name") String name,HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
    ServletContext servletContext = webApplicationContext.getServletContext();
    servletContext.getRequestDispatcher("/"+name);
    PrintWriter out = response.getWriter();
    out.println("UnValidated Forward");
  }


  @RequestMapping("/pathVariable003.do")
  public void pathVariable003(HttpServletRequest request, HttpServletResponse response) {
    try {
      String location = request.getRequestURI();
      String uri = location.substring(0,location.indexOf("/pathVariable003"));
      response.sendRedirect(uri+"/index");
      PrintWriter out = response.getWriter();
      out.println("UnValidated Redirect");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/{param}/pathVariable004.do")
  public void pathVariable004(@PathVariable String param, HttpServletRequest request, HttpServletResponse response) {
    try {
      String location = request.getRequestURI();
      String uri = location.substring(0,location.indexOf("/redirect")+"/redirect".length());
      response.sendRedirect(uri+"/index");
      PrintWriter out = response.getWriter();
      out.println("UnValidated Redirect");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/pathVariable005.do")
  public void pathVariable005(HttpServletRequest request, HttpServletResponse response) {
    try {
      StringBuffer location = request.getRequestURL();
      String uri = location.substring(0,location.indexOf("/pathVariable005"));
      response.sendRedirect(uri+"/index");
      PrintWriter out = response.getWriter();
      out.println("UnValidated Redirect");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/{param}/pathVariable006.do")
  public void pathVariable006(@PathVariable String param, HttpServletRequest request, HttpServletResponse response) {
    try {
      StringBuffer location = request.getRequestURL();
      String uri = location.substring(0,location.indexOf("/redirect")+"/redirect".length());
      response.sendRedirect(uri+"/index");
      PrintWriter out = response.getWriter();
      out.println("UnValidated Redirect");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/index")
  public String index(HttpServletRequest request, HttpServletResponse response) {
    return "index";
  }

}
