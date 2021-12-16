package org.spring.demo.controller.sensitivedatatrack.httpclient3;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

public class HttpClient3Util {

  private static final Logger logger = LoggerFactory.getLogger(HttpClient3Util.class);

  /**
   * get请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri
   */
  public static RestApiResponseVo doGet(String... args) {
    String resStr = null;
    int statusCode = 0;
    HttpClient httpClient = new HttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    GetMethod getMethod = new GetMethod(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    getMethod.addRequestHeader("Content-Type", "application/json");
    getMethod.addRequestHeader("Authorization", "Basic " + authHead);
    getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    try {
      statusCode = httpClient.executeMethod(getMethod);
      logger.debug("statusCode================>" + statusCode);
      resStr = IOUtils.toString(getMethod.getResponseBodyAsStream(), "utf-8");
      logger.debug("resStr================>" + resStr);
      jiraResponseVo.setStatusCode(statusCode);
      jiraResponseVo.setResponseBody(resStr);
    } catch (Exception e) {
      logger.error("doGet execute fail: " + e.getMessage());
    } finally {
      getMethod.releaseConnection();
    }
    return jiraResponseVo;
  }

  /**
   * get请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri
   */
  public static RestApiResponseVo doGetForAviodRepeat(String... args) {
    String resStr = null;
    int statusCode = 0;
    HttpClient httpClient = new HttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    GetMethod getMethod = new GetMethod(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    getMethod.addRequestHeader("Content-Type", "application/json");
    getMethod.addRequestHeader("Authorization", "Basic " + authHead);
    getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    try {
      statusCode = httpClient.executeMethod(getMethod);
      logger.debug("statusCode================>" + statusCode);
      resStr = IOUtils.toString(getMethod.getResponseBodyAsStream(), "utf-8");
      logger.debug("resStr================>" + resStr);
      jiraResponseVo.setStatusCode(statusCode);
      jiraResponseVo.setResponseBody(resStr);
    } catch (Exception e) {
      logger.error("doGet execute fail: " + e.getMessage());
    } finally {
      getMethod.releaseConnection();
    }
    return jiraResponseVo;
  }

  /**
   * post 请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri,arg[3]=jsonParameters
   */
  public static RestApiResponseVo doPost(String... args) {
    String resStr = null;
    int statusCode = 0;
    HttpClient htpClient = new HttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    PostMethod postMethod = new PostMethod(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    postMethod.addRequestHeader("Content-Type", "application/json");
    postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    postMethod.addRequestHeader("Authorization", "Basic " + authHead);
    postMethod.setRequestBody(args[3]);
    try {
      statusCode = htpClient.executeMethod(postMethod);
      logger.debug("statusCode================>" + statusCode);
      resStr = IOUtils.toString(postMethod.getResponseBodyAsStream(), "utf-8");
      logger.debug("resStr================>" + resStr);
      jiraResponseVo.setStatusCode(statusCode);
      jiraResponseVo.setResponseBody(resStr);
    } catch (Exception e) {
      logger.error("doPost execute fail: " + e.getMessage());
    } finally {
      postMethod.releaseConnection();
    }
    return jiraResponseVo;
  }

  public static RestApiResponseVo doPost1(String... args)  {
    HttpClient httpClient = new HttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    String contentType = "";
    PostMethod postMethod = new PostMethod(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    Object paramMap = args[3];
    postMethod.addParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    if (paramMap instanceof String) {
      contentType = "application/json;charset=utf-8";
      RequestEntity re = null;
      try {
        re = new StringRequestEntity((String) paramMap , "application/json" , "UTF-8");
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      postMethod.setRequestEntity(re);
    }
    else {
      HashMap<String, Object> params = (HashMap<String, Object>) paramMap;
      contentType = "application/x-www-form-urlencoded;charset=utf-8";

      for (Map.Entry<String, Object> entry : params.entrySet()) {
        postMethod.addParameter(entry.getKey(), entry.getValue().toString());
      }
    }
    postMethod.addRequestHeader("Authorization", "Basic " + authHead);
    postMethod.addRequestHeader("Content-Type", contentType);
    try {
      int statusCode = httpClient.executeMethod(postMethod);
      logger.debug("statusCode==>" + statusCode);
      String resStr = IOUtils.toString(postMethod.getResponseBodyAsStream(), "utf-8");
      logger.debug("resStr==>" + resStr);
      System.out.println("resStr==>" + resStr);
      jiraResponseVo.setStatusCode(statusCode);
      jiraResponseVo.setResponseBody(resStr);
    }
    catch (Exception e) {
      logger.error("doPost execute fail: " + e.getMessage());
    }
    finally {
      postMethod.releaseConnection();
    }
    return jiraResponseVo;
  }

  private static String addBasicAuthHead(String username, String password) {
    String strAuth = username + ":" + password;
    String basicAuthHead = "";
    try {
      basicAuthHead = DatatypeConverter.printBase64Binary(strAuth.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException e) {
      logger.error("authHead don't support UTF-8: " + e.getMessage());
    }
    return basicAuthHead;
  }

}
