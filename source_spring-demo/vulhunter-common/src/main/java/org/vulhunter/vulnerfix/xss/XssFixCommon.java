package org.vulhunter.vulnerfix.xss;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.vulhunter.common.reflectedxss.StringHttpMessageConverterExtends;

public class XssFixCommon {
	public void writeInternalFix(HttpServletRequest request, HttpServletResponse response) {
		String inputName = request.getParameter("username");
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		try {
			StringHttpMessageConverterExtends stringHttpMessageConverterExtends = new StringHttpMessageConverterExtends();
			// spread java.net.URLEncoder.encode
			// P1-R
			String URLEncode1 = ESAPI.encoder().encodeForJavaScript(inputName);
			stringHttpMessageConverterExtends.getWriteInternal(URLEncode1, outputMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
