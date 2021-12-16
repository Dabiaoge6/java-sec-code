package org.spring.demo.controller.vulnercontroller.loginjection;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("logInjection")
public class Log4jInjectionController {

  private static Logger logger = Logger.getLogger("Log4jInjectionController.class");

  @RequestMapping("/log003.do")
  public void log1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String msg = request.getParameter("msg");
    logger.log(Level.INFO, msg, new Throwable());
    PrintWriter out = response.getWriter();
    out.println("<font color=red>Log-Injection.</font>");
    out.flush();
    out.close();

  }

  @RequestMapping("/log004.do")
  public void log2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String msg = request.getParameter("msg");
    logger.log(Level.INFO, msg);
    PrintWriter out = response.getWriter();
    out.println("<font color=red>Log-Injection.</font>");
    out.flush();
    out.close();

  }
	
	/*@RequestMapping("/log005.do")
	public void log3(HttpServletRequest request,HttpServletResponse response){
		String msg = request.getParameter("msg");
		logger.log(Level.INFO,msg,(Object)null);
		
	}*/

}
