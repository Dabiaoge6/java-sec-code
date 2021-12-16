package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class HeaderInjectionTest {
	
	@Test
	public void headerInjection1(){
		String path = "/headerInjection/headerInjection1.do?url=test";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void headerInjection2(){
		String path = "/headerInjection/headerInjection2.do?url=test";
		HttpClientUtil.get(path);
	}

}
