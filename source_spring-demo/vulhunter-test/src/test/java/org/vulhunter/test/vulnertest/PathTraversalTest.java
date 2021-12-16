package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class PathTraversalTest {
	
	@Test
	public void fileInitString(){
		String path = "/pathtraversal/fileInitString.do?path=";
	}
	
	@Test
	public void upload(){
		String path = "/pathtraversal/upload.do";
		HttpClientUtil.uploadFile(path, "‪D:/test/csrf.xml");
	}

}
