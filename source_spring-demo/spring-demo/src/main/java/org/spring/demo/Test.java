package org.spring.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.spring.demo.util.RSAUtils;

public class Test {

  public static void main(String[] args){
    User user = new User();
    user.setName("hujj");
    user.setPwd("test");


    // 执行post请求
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonStr = objectMapper.writeValueAsString(user);

      CloseableHttpClient httpclient = HttpClients.createDefault();
      System.out.println(jsonStr);

      /*HttpPost httpPost = new HttpPost("http://localhost:8086/test-demo/sqlInjection/testEncryption1.do");
      httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
      // 解决中文乱码问题
      String encrStr = RSAUtils.encrypt(jsonStr);
      StringEntity stringEntity = new StringEntity(encrStr, "UTF-8");
      stringEntity.setContentEncoding("UTF-8");

      httpPost.setEntity(stringEntity);*/
      CloseableHttpResponse response = httpclient.execute(testEncryption1(jsonStr));

      // 获取响应
      getResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static HttpPost testEncryption1(String jsonStr){
    HttpPost httpPost = new HttpPost("http://localhost:8086/test2-demo/sqlInjection/testEncryption1.do");
    httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
    String encrStr = null;
    try {
      encrStr = RSAUtils.encrypt(jsonStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    StringEntity stringEntity = new StringEntity(encrStr, "UTF-8");
    stringEntity.setContentEncoding("UTF-8");

    httpPost.setEntity(stringEntity);
    return httpPost;
  }

  private static HttpPost noEncryption2(String jsonStr){
    HttpPost httpPost = new HttpPost("http://localhost:8086/test1-demo/sqlInjection/noEncryption2.do");
    httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
    StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
    stringEntity.setContentEncoding("UTF-8");
    httpPost.setEntity(stringEntity);
    return httpPost;
  }


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
