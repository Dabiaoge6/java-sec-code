package org.spring.demo.controller.vulnercontroller.loginjection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

@Controller
@RequestMapping("logInjection")
public class LogInjectionController extends HttpServlet implements ServletContextAware {

	private ServletContext context;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	@Override
	public void log(String msg) {
		context.log(msg);
	}

	@Override
	public void log(String message, Throwable t) {
		context.log(message, t);
	}

	@RequestMapping("/log001.do")
	public void logStr(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String msg = request.getParameter("msg");
		log(msg);
		//ResponseUtil.setAttrbute(response);
		PrintWriter out = response.getWriter();

		//out.println("<font color=red>Log-Injection.</font>");
		out.println("上报漏洞：日志注入漏洞");
		out.flush();
		out.close();

	}

	@RequestMapping("/log002.do")
	public void logThrow(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String msg = request.getParameter("msg");
		Throwable t = new Throwable();
		log(msg, t);
		PrintWriter out = response.getWriter();
		out.println("<font color=red>Log-Injection.</font>");
		out.flush();
		out.close();
	}

}
