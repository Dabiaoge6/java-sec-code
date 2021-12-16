package org.vulhunter.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {

	private static String domain = "http://localhost:8086/tomcat6-demo";

	/*
	 * get
	 */
	public static void get(String path) {
		String uri = domain + path;
		// 创建Httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 定义get方式
		HttpGet get = new HttpGet(uri);

		try {
			// 发送get请求
			CloseableHttpResponse response = httpclient.execute(get);

			// 获取响应
			getResponse(response);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * paramForm 表单数据 path 部分请求url
	 */
	public static void postForm(String path, List paramForm) {
		String uri = domain + path;
		// 创建Httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 定义post方式
		HttpPost post = new HttpPost(uri);

		// 设置表单数据
		List paramList = paramForm;
		try {
			// 获取requestbody
			UrlEncodedFormEntity encodeForm = new UrlEncodedFormEntity(paramList, "UTF-8");
			post.setEntity(encodeForm);

			// 执行post请求
			CloseableHttpResponse response = httpclient.execute(post);

			// 获取响应
			getResponse(response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 上传文件
	 */
	public static void uploadFile(String path, String fileName) {
		String uri = domain + path;
		// 创建httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建post
		HttpPost post = new HttpPost(uri);

		// 设置参数
		FileEntity fileEntity = new FileEntity(new File(fileName));
		post.setEntity(fileEntity);

		try {
			// 发起请求
			CloseableHttpResponse response = httpclient.execute(post);

			// 获取响应
			getResponse(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 设置自定义header
	 */
	public static void setHeader(String path) {
		String uri = domain + path;
		// 创建Httpclient
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 定义get方式
		HttpGet get = new HttpGet(uri);

		// 设置请求头
		get.setHeader("password", "test123!");

		try {
			// 发送get请求
			CloseableHttpResponse response = httpclient.execute(get);

			// 获取响应
			getResponse(response);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取响应
	 */
	private static void getResponse(CloseableHttpResponse response) {
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream in;
			try {
				in = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String str = "";
				while ((str = br.readLine()) != null) {
					sb.append(str);
				}
				System.out.println("result=====>" + sb.toString());
			} catch (UnsupportedOperationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
