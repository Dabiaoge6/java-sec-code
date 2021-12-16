package org.vulhunter.test.vulnertest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.vulhunter.test.util.HttpClientUtil;

public class CSRFTest {
	
	/*
	 * 设置参数
	 */
	private List setParam(){
		List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("param", "test-csrf"));
		return paramList;
	}
	
	@Test
	public void fileOutputStream(){
		String path = "/csrf/fileOutputStream.do";
		List paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
		
	}
	
	@Test
	public void printWrite(){
		String path = "/csrf/printWriter.do";
		List paramForm = setParam();
		HttpClientUtil.postForm(path, paramForm);
	}

	@Test
	public void insertData(){
		String path = "/csrf/insert.do";
		List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("name", "csrf"));
		paramList.add(new BasicNameValuePair("pwd", "123"));
		paramList.add(new BasicNameValuePair("age", "12"));
		HttpClientUtil.postForm(path, paramList);
	}
	
	@Test
	public void updateData(){
		String path = "/csrf/update.do";
		List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("name_update", "csrf"));
		paramList.add(new BasicNameValuePair("pwd_update", "123"));
		paramList.add(new BasicNameValuePair("newpwd", "1234"));
		paramList.add(new BasicNameValuePair("confirmpwd", "1234"));
		HttpClientUtil.postForm(path, paramList);
	}
	
	@Test
	public void deleteData(){
		String path = "/csrf/delete.do";
		List paramList = new ArrayList();
		paramList.add(new BasicNameValuePair("username", "csrf"));
		HttpClientUtil.postForm(path, paramList);
	}
	
}
