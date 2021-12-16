package org.vulhunter.test.vulnertest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CryptoMacTest {
	
	private String[] args = {"SHA-1","SHA-224","SHA-256","SHA-384","SHA-512","MD5"};
	
	private List<List<BasicNameValuePair>> setParam(){
		List<List<BasicNameValuePair>> paramList = new ArrayList<List<BasicNameValuePair>>();
		for(String arg:args){
			List<BasicNameValuePair> paramForm = new ArrayList<BasicNameValuePair>();
			paramForm.add(new BasicNameValuePair("hashName", arg));
			paramList.add(paramForm);
		}
		return paramList;
	}
	
	@Test
	public void method1(){
		String path = "/mac/method1.do";
		List<List<BasicNameValuePair>> paramList = setParam();
		for(List<BasicNameValuePair> paramForm:paramList){
			HttpClientUtil.postForm(path, paramForm);
		}
	}
	
	@Test
	public void method2(){
		String path = "/mac/method2.do";
		List<List<BasicNameValuePair>> paramList = setParam();
		for(List<BasicNameValuePair> paramForm:paramList){
			HttpClientUtil.postForm(path, paramForm);
		}
	}
	
	@Test
	public void method3(){
		String path = "/mac/method3.do";
		List<List<BasicNameValuePair>> paramList = setParam();
		for(List<BasicNameValuePair> paramForm:paramList){
			HttpClientUtil.postForm(path, paramForm);
		}
	}

}
