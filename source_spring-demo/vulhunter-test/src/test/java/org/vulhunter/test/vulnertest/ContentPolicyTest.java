package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class ContentPolicyTest {
	
	@Test
	public void cspTest(){
		String path = "/contentPolicy/csp.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cspMissingTest(){
		String path = "/contentPolicy/cspMissing.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cspResponseMissingTest(){
		String path = "/contentPolicy/cspResponseMissing.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cspResponseTest(){
		String path = "/contentPolicy/cspResponse.do";
		HttpClientUtil.get(path);
	}

}
