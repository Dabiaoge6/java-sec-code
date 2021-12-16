package org.spring.demo.controller.vulnercontroller;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cipher")
public class CipherController {

	@RequestMapping("/cipher002.do")
	public void method2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String transformation = request.getParameter("transformation");
		boolean res = transformation.matches("^(DESede|AES|RSA).*$");
		Provider[] providers = Security.getProviders("Cipher.AES");
		for(int i=0;i<providers.length;i++){
			Cipher instance = javax.crypto.Cipher.getInstance(transformation, providers[0]);
		}
		//response.getWriter().print(res + "   :" + transformation);
		response.getWriter().print("上报漏洞：不安全的加密算法");

	}
}
