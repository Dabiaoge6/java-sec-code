package org.spring.demo.controller.vulnercontroller;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;

@Controller
@RequestMapping("mac")
public class MacController {
	
	@RequestMapping("/mac001.do")
	public void method1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hashName = request.getParameter("hashName");
		try {
			String hashString = "CryptoBadMac";
			if(hashName.equals("SHA-224")){
				Security.addProvider(new BouncyCastleProvider());
			}
			MessageDigest instance = MessageDigest.getInstance(hashName);
			//response.getWriter().print(hashString + "**method1**" + "hashName" + " ***** " + instance.digest(hashString.getBytes()));
			response.getWriter().print("上报漏洞：不安全的哈希算法");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
