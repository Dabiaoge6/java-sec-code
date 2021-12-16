package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("clickControl")
public class ClickjackController {

	@RequestMapping("/click001.do")
	public void clickJack(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-Frame-Options", "");
		PrintWriter out = response.getWriter();
		out.println("the request  report clickjacking-control-missing");
	}

	@RequestMapping("/click002.do")
	public void clickJackDENY(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-Frame-Options", "DENY");
		PrintWriter out = response.getWriter();
		out.println("the request don't  report clickjacking-control-missing");
	}

	@RequestMapping("/click003.do")
	public void clickJackSAM(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		PrintWriter out = response.getWriter();
		out.println("the request don't  report clickjacking-control-missing");
	}

	@RequestMapping("/click004.do")
	public void clickJackALLOW(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-Frame-Options", "ALLOW-FROM uri");
		PrintWriter out = response.getWriter();
		out.println("the request don't  report clickjacking-control-missing");
	}

	@RequestMapping("/click005.do")
	public void clickJackError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("X-Frame-Options", "Error");
		PrintWriter out = response.getWriter();
		out.println("the request report clickjacking-control-missing");
	}

}
