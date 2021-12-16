package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CookieSecureTest {
	
	@Test
	public void TrueSecure(){
		String path = "/cookieSecure/secure_true.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void FalseSecure(){
		String path = "/cookieSecure/secure_false.do";
		HttpClientUtil.get(path);
	}



}
