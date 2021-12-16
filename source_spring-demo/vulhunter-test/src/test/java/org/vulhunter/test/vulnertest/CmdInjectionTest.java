package org.vulhunter.test.vulnertest;

import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CmdInjectionTest {
	
	@Test
	public void cmdInjection1(){
		String path = "/cmdInjection/cmdInjection1.do?cmd=calc";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection2(){
		String path = "/cmdInjection/cmdInjection2.do?cmd=cme.exe&cmd=/c&cmd=dir";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection3(){
		String path = "/cmdInjection/cmdInjection3.do?cmd=cmd.exe&cmd=/c&cmd=dir";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection4(){
		String path = "/cmdInjection/cmdInjection4.do?cmd=cmd.exe&cmd=/c&cmd=dir";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection5(){
		String path = "/cmdInjection/cmdInjection5.do?cmd=mspaint";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection6(){
		String path = "/cmdInjection/cmdInjection6.do?cmd=cmd.exe /c dir";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void cmdInjection7(){
		String path = "/cmdInjection/cmdInjection7.do?cmd=cmd.exe&cmd=/c&cmd=dir";
		HttpClientUtil.get(path);
	}

	@Test
	public void cmdInjection8(){
		String path = "/cmdInjection/cmdInjection8.do?cmd=cmd.exe&cmd=/c&cmd=dir";
		HttpClientUtil.get(path);
	}
}
