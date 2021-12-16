package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class ClickJackTest {
	
	@Test
	public void clickJack(){
		String path = "/clickControl/clickjacking.do";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void clickJackDENY(){
		String path = "/clickControl/clickjacking_DENY.do";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void clickJackSAM(){
		String path = "/clickControl/clickjacking_SAM.do";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void clickjackingALLOW(){
		String path = "/clickControl/clickjacking_ALLOW.do";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void clickjackingError(){
		String path = "/clickcontrol/clickjacking_Error.do";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void noclickjacking(){
		String path = "/clickcontrol/noclickjacking.jsp";
		HttpClientUtil.get(path);
		
	}
	
	@Test
	public void sestclickjacking(){
		String path = "/clickControl/setclickjacking.jsp";
		HttpClientUtil.get(path);
		
	}

}
