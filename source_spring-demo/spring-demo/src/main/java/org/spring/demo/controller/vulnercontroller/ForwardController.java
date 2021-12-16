package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("forward")
public class ForwardController {

  // getRequestDispatcher -> javax.servlet.ServletContext.getRequestDispatcher
  @RequestMapping("/forward001.do")
  public void forward2(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
    ServletContext servletContext = webApplicationContext.getServletContext();
    String name = request.getParameter("name");
    servletContext.getRequestDispatcher(name);
    PrintWriter out = response.getWriter();
    out.println("UnValidated Forward");
  }

  // setAttribute("location", location) ->
  // javax.servlet.jsp.PageContext.forward
	/*@RequestMapping("/forward2.do")
	public String forward1(HttpServletRequest request, HttpServletResponse response) {
		String location = request.getParameter("location");
		request.setAttribute("location", location);
		return "pagecontext";
	}*/

}
