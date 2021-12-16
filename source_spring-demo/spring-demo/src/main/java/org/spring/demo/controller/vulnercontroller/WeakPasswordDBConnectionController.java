package org.spring.demo.controller.vulnercontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weakPasswordDBConnection")
public class WeakPasswordDBConnectionController {

	@RequestMapping("/weakPwd001.do")
	public void weakPwdTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String url = "jdbc:mysql://db.hf.seczone.cn:3306/APPdb?user=seczone&password=seczone@123&autoReconnect=true";
//      String url = "jdbc:mysql://localhost:3306/appdb?user=root&password=root&autoReconnect=true";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://db.hf.seczone.cn:3306/APPdb","root","seczone@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
