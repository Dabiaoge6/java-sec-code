package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class ElInjectionTest {
	
	@Test
	public void jstl_el_post(){
		String path = "/elinjection/jstl_el_post.do";
		HttpClientUtil.get(path);
	}
	
	//tomcat7
	@Test
	public void jstl_el(){
		String path = "/elinjection/jstl_el.jsp?message=%22${applicationScope}%22";
		HttpClientUtil.get(path);
	}

}
