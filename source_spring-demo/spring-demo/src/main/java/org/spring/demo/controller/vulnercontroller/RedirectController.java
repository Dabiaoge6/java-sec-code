package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import play.mvc.Results;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("redirect")
public class RedirectController {


	// redirect -> play.mvc.Results.redirect
	@RequestMapping("/redirect002.do")
	public void redirect2(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String location = request.getParameter("location");
		Results.redirect(location);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：未经校验的客户端重定向");
	}

	// getRequestDispatcher -> javax.servlet.ServletContext.getRequestDispatcher
	@RequestMapping("/redirect005.do")
	public void forward2(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext.getServletContext();
		String name = request.getParameter("name");
		servletContext.getRequestDispatcher(name);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("上报漏洞：未经校验的服务端重定向");
	}
}
