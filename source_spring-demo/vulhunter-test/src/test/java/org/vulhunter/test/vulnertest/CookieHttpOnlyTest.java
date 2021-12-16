package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CookieHttpOnlyTest {
	
	@Test
	public void httpOnly(){
		String path = "/cookiehttp/httpOnly.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void httpOnlyFalse(){
		String path = "/cookiehttp/httpOnly_false.do";
		HttpClientUtil.get(path);
	}
	
	

}
