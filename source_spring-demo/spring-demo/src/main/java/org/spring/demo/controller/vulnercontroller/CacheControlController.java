package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cacheControl")
public class CacheControlController {

	@RequestMapping("/cache001.do")
	public void cacheControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.println("the request don't report Cache-Control-missing");
	}

	@RequestMapping("/cache002.do")
	public void noCacheControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("the request report Cache-Control-missing");
	}

}
