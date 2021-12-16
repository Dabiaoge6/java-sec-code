package org.spring.demo.controller.vulnercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
@RequestMapping("unsafeConnection")
public class UnsafeConnectionController {


	@RequestMapping("/unsafeTest.do")
	public void unsafeTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String url = request.getParameter("url");
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection(url);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.println("上报漏洞：1.数据库连接字符串注入 2.不安全的哈希算法 3.跨站请求伪造");
		}
	}

	@RequestMapping("/unsafeDbConnection.do")
	public void unsafeDbConnection(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String url = request.getParameter("url");
			String uasename = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection(url,uasename,pwd);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.println("上报漏洞：1.数据库连接字符串注入 2.弱密码数据库连接 3.不安全的哈希算法");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
