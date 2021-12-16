package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class HardKeyTest {
	
	@Test
	public void hardKey(){
		String path ="/hardkey/hardkey.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void hardSecret(){
		String path ="/hardkey/secretkey.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void encryption(){
		String path ="/hardkey/no_encryption.do";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void testEncryption(){
		String path ="/hardkey/testencryption.do";
		HttpClientUtil.get(path);
	}

}
