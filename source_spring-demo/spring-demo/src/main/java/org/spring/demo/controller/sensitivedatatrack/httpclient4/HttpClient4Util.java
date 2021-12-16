package org.spring.demo.controller.sensitivedatatrack.httpclient4;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

public class HttpClient4Util {

  private static final Logger logger = LoggerFactory.getLogger(HttpClient4Util.class);

  /**
   * get请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri
   */
  public static RestApiResponseVo doGet(String... args) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    // 定义get方式
    HttpGet getMethod = new HttpGet(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    getMethod.setHeader("Content-Type", "application/json");
    getMethod.setHeader("Authorization", "Basic " + authHead);
    getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    try {
      CloseableHttpResponse response = httpclient.execute(getMethod);
      jiraResponseVo = getResponseVo(response);
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
    CloseableHttpClient httpclient = HttpClients.createDefault();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    // 定义get方式
    HttpGet getMethod = new HttpGet(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    getMethod.setHeader("Content-Type", "application/json");
    getMethod.setHeader("Authorization", "Basic " + authHead);
    getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    try {
      CloseableHttpResponse response = httpclient.execute(getMethod);
      jiraResponseVo = getResponseVo(response);
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
    CloseableHttpClient httpclient = HttpClients.createDefault();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    HttpPost postMethod = new HttpPost(args[2]);
    String authHead = addBasicAuthHead(args[0], args[1]);
    postMethod.setHeader("Content-Type", "application/json");
    postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
    postMethod.setHeader("Authorization", "Basic " + authHead);
    try {
      postMethod.setEntity(new StringEntity(args[3],"UTF-8"));
      CloseableHttpResponse response = httpclient.execute(postMethod);
      jiraResponseVo = getResponseVo(response);
    } catch (Exception e) {
      logger.error("doPost execute fail: " + e.getMessage());
    } finally {
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

  private static RestApiResponseVo getResponseVo(CloseableHttpResponse response)
      throws IOException {
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    logger.debug("statusCode================>" + response.getStatusLine().getStatusCode());
    String resStr = IOUtils.toString(response.getEntity().getContent(), "utf-8");
    logger.debug("resStr================>" + resStr);
    jiraResponseVo.setStatusCode(response.getStatusLine().getStatusCode());
    jiraResponseVo.setResponseBody(resStr);
    return jiraResponseVo;
  }

}
