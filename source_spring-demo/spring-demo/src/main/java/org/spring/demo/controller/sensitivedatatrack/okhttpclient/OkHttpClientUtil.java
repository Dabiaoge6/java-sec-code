package org.spring.demo.controller.sensitivedatatrack.okhttpclient;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

public class OkHttpClientUtil {

  private static final Logger logger = LoggerFactory.getLogger(OkHttpClientUtil.class);

  /**
   * get请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri
   */
  public static RestApiResponseVo doGet(String... args) {
    OkHttpClient httpClient = new OkHttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    String authHead = addBasicAuthHead(args[0], args[1]);
    Request getMethod = new Request.Builder()
        .url(args[2])
        .addHeader("Authorization", "Basic " + authHead)
        .addHeader("Content-Type", "application/json")
        .addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8")
        .build();//创建Request 对象
    try {
      Call call = httpClient.newCall(getMethod);
      Response response = call.execute();
      logger.debug("statusCode================>" + response.code());
      logger.debug("res message================>" + response.message());
      logger.debug("res body================>" + response.body().string());
      jiraResponseVo.setStatusCode(response.code());
      jiraResponseVo.setResponseBody(response.body().string());
    } catch (Exception e) {
      logger.error("doGet execute fail: " + e.getMessage());
    }
    return jiraResponseVo;
  }

  /**
   * get请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri
   */
  public static RestApiResponseVo doGetAsync(String... args) throws InterruptedException {
    OkHttpClient httpClient = new OkHttpClient();
    String authHead = addBasicAuthHead(args[0], args[1]);
    Request getMethod = new Request.Builder()
        .url(args[2])
        .addHeader("Authorization", "Basic " + authHead)
        .addHeader("Content-Type", "application/json")
        .addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8")
        .build();
    final RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    try {
      httpClient.newCall(getMethod).enqueue(new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {

        }

        @Override
        public void onResponse(Response response) throws IOException {
          if (response.isSuccessful()) {
            logger.debug("statusCode================>" + response.code());
            logger.debug("res message================>" + response.message());
            logger.debug("res body================>" + response.body().string());
            jiraResponseVo.setStatusCode(response.code());
            jiraResponseVo.setResponseBody(response.body().string());
          }
        }
      });
    } catch (Exception e) {
      logger.error("doGet execute fail: " + e.getMessage());
    }
    Thread.sleep(5000);
    return jiraResponseVo;
  }

  /**
   * post 请求
   *
   * @param args 请求参数 args[0]=username，args[1]=password,args[2]=reqUri,arg[3]=jsonParameters
   */
  public static RestApiResponseVo doPost(String... args) {
    OkHttpClient htpClient = new OkHttpClient();
    RestApiResponseVo jiraResponseVo = new RestApiResponseVo();
    String authHead = addBasicAuthHead(args[0], args[1]);
    RequestBody body = RequestBody
        .create(MediaType.parse("application/json; charset=utf-8"), args[3]);
    Request postMethod = new Request.Builder()
        .url(args[2])
        .addHeader("Authorization", "Basic " + authHead)
        .addHeader("Content-Type", "application/json")
        .addHeader(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8")
        .post(body)
        .build();
    try {
      Response response = htpClient.newCall(postMethod).execute();
      logger.debug("statusCode================>" + response.code());
      logger.debug("resStr================>" + response.message());
      logger.debug("resBody================>" + response.body().string());
      jiraResponseVo.setStatusCode(response.code());
      jiraResponseVo.setResponseBody(response.body().string());
    } catch (Exception e) {
      logger.error("doPost execute fail: " + e.getMessage());
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
