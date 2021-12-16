package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class HardPasswordTest {
	
	@Test
	public void hardPassword(){
		String path = "/hardpwd/hardpassword.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void encodePassword(){
		String path = "/hardpwd/encodepassword.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void shortPassword(){
		String path = "/hardpwd/shortpassword.do";
		HttpClientUtil.get(path);
	}

}
