package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class HSTSTest {
	
	@Test
	public void hstsNo(){
		String path = "/hsts/hsts.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void hstsMaxage(){
		String path = "/hsts/maxage_null.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void httpsMaxage(){
		String path = "/hsts/maxage1.do";
		HttpClientUtil.get(path);
	}

}
