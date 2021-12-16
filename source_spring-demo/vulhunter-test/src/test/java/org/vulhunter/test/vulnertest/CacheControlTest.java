package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CacheControlTest {
	
	@Test
	public void cacheControl(){
		String path = "/cacheControl/cacheControl.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void setcache(){
		String path = "/cachecontrol/setcache.jsp";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void nocache(){
		String path = "/cachecontrol/nocache.jsp";
		HttpClientUtil.get(path);
	}

}
