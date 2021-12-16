package org.vulhunter.test.vulnertest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class IsitTest {
	
	private List<BasicNameValuePair> setParam(){
		List<BasicNameValuePair> paramList= new ArrayList<BasicNameValuePair>();
		paramList.add(new BasicNameValuePair("password", "test123!"));
		return paramList;
	}
	
	@Test
	public void isit1(){
		String path = "/isit/isit1.do?password=hujj@seczone.cn";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void isit2(){
		String path = "/isit/isit2.do?account=8792749207414";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void isit3(){
		String path = "/isit/isit3.do?mobile=18736529067";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void isit4(){
		String path = "/isit/isit4.do?name=start";
		HttpClientUtil.get(path);
	}
	
	@Test
	public void isit1Post(){
		String path = "/isit/isit1_post.do";
		List<BasicNameValuePair> paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
	}
	
	@Test
	public void isit2Post(){
		String path = "/isit/isit2_post.do";
		List<BasicNameValuePair> paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
	}
	
	@Test
	public void isit3Post(){
		String path = "/isit/isit3_post.do";
		List<BasicNameValuePair> paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
	}
	
	@Test
	public void isit4Post(){
		String path = "/isit/isit4_post.do";
		List<BasicNameValuePair> paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
	}
	
	@Test
	public void isit1Header(){
		String path = "/isit/isit1_header.do";
		HttpClientUtil.setHeader(path);
	}
	
	@Test
	public void isit2Header(){
		String path = "/isit/isit2_header.do";
		HttpClientUtil.setHeader(path);
	}
	
	@Test
	public void isit3Header(){
		String path = "/isit/isit3_header.do";
		HttpClientUtil.setHeader(path);
	}
	
	@Test
	public void isit4Header(){
		String path = "/isit/isit4_header.do";
		HttpClientUtil.setHeader(path);
	}
	
}
