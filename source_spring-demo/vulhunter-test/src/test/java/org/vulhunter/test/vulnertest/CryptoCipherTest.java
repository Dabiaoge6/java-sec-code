package org.vulhunter.test.vulnertest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CryptoCipherTest {

	private String[] args = { "AES/CBC/NoPadding", "AES/CBC/PKCS5Padding", "AES/ECB/NoPadding", "AES/ECB/PKCS5Padding",
			"DES/CBC/NoPadding", "DES/CBC/PKCS5Padding", "DES/ECB/NoPadding", "DES/ECB/PKCS5Padding",
			"DESede/CBC/NoPadding", "DESede/CBC/PKCS5Padding", "DESede/ECB/NoPadding", "DESede/ECB/PKCS5Padding",
			"RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding" };

	private List<List<BasicNameValuePair>> setParam() {
		List<List<BasicNameValuePair>> params = new ArrayList<List<BasicNameValuePair>>();
		for (String transformation : args) {
			List<BasicNameValuePair> paramList = new ArrayList<BasicNameValuePair>();
			paramList.add(new BasicNameValuePair("transformation", transformation));
			params.add(paramList);
		}
		return params;
	}
	
	@Test
	public void cryptoBadCipher1() {
		String path = "/cipher/cryptoBadCipher1.do";
		List<List<BasicNameValuePair>> params = setParam();
		for(List<BasicNameValuePair> paramForm:params){
			HttpClientUtil.postForm(path, paramForm);
		}
	}

	@Test
	public void cryptoBadCipher2() {
		String path = "/cipher/cryptoBadCipher2.do";
		List<List<BasicNameValuePair>> params = setParam();
		for(List<BasicNameValuePair> paramForm:params){
			HttpClientUtil.postForm(path, paramForm);
		}
	}

	@Test
	public void cryptoBadCipher3() {
		String path = "/cipher/cryptoBadCipher3.do";
		List<List<BasicNameValuePair>> params = setParam();
		for(List<BasicNameValuePair> paramForm:params){
			HttpClientUtil.postForm(path, paramForm);
		}
	}

}
